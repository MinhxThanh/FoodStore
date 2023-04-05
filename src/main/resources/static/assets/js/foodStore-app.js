const app = angular.module("foodStore-app", ['ngAnimate'])
app.controller("foodStore-controller", function ($scope, $http, $window) {
    $scope.items = []
    $scope.address = {}
    $scope.message = ''
    $scope.error = ''

    $scope.initialize = function (){
        //get list food
        $http.get(`/rest/food/getListFood`).then(resp => {
            $scope.items = resp.data
        })
        //get all items  in cart by customer
        $http.get(`/rest/cart/getAll`).then(resp => {
            if (resp.data != null)
                for (let item of resp.data) {
                    $scope.cart.addToSyn(item.food.id, item.quantity)
                }
        })
        //get info address of customer
        $http.get(`/rest/customerPhoneAddress/findByCustomerEmail`).then(resp => {
            $scope.address = angular.copy(resp.data)
        })
    }

    $scope.initialize()


    $scope.checkout = {
        couponCode: '',
        couponItem: {},
        address: {},
        addresses: [],
        coupons: [],
        paymentMethod: true,
        items: [],
        get amount() {
            return this.items
                .map(item => item.quantity * item.priceNew)
                .reduce((total, quantity) => total += quantity, 0)
        },
        get shippingPrice(){
            if (this.amount > 50000)
                return 0
            else
                return 15000
        },
        get totalPrice(){
            if (this.couponItem.name != null) {
                if (this.couponItem.amountMoneyCoupon > 0) {
                    return Number((this.amount + this.shippingPrice) - this.couponItem.amountMoneyCoupon)
                } else {
                    return Number(((this.amount + this.shippingPrice) * ((100 - this.couponItem.percentCoupon) / 100)))
                }
            } else {
                return Number(this.amount + this.shippingPrice)
            }
        },
        getInfoCoupon(){
            $http.get(`/rest/coupon/findByCode/${this.couponCode}`).then(resp => {
                this.couponItem = angular.copy(resp.data)
            })
        },
        getUserInfoCoupon() {
            $http.get(`/rest/coupon/findAllCouponByCustomer`).then(resp =>{
                this.coupons = resp.data
            }).catch(err => console.log("coupon: ", err))
        },
        getUserInfoAddress() {
            $http.get(`/rest/customerPhoneAddress/findAllByCustomerEmail`).then(resp => {
                this.addresses = resp.data
            })
        },
        changeAddress(item){
            this.address = angular.copy(item)
        },
        getItems(item){
            let index = this.items.findIndex(t => t.id == item.id)

            if (index == -1)
                this.items.push(item)
            else
                this.items.splice(index, 1)
            console.log("checout items: ", this.items)
            this.saveToSessionStorage()
        },
        saveToSessionStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            sessionStorage.setItem("checkoutItems", json);
        },
        async loadFromSessionStorage() {
            let json = sessionStorage.getItem("checkoutItems")
            this.items = json ? JSON.parse(json) : []
            await $scope.sleep(3000)
            // sessionStorage.removeItem("checkoutItems");
        }
    }



    //management order
    $scope.order = {
        orderDate: new Date(),
        shippedDate: '',
        shipmethod: {id: 1},
        customerPhoneAddress: {},
        coupon: {},
        customer: {},
        paymentmethod: {},

        image: '',
        price: '',
        display: true,
        status: 1,
        getUserInfo(){
            // set image
            let firstItem = $scope.checkout.items[0];
            this.image = firstItem.image;

            this.price = $scope.checkout.totalPrice
            this.shippedDate = $scope.addMinutesToDate(60)

            //add customer info here
            $http.get(`/rest/customer/findCurrentCustomer`).then(resp =>{
                this.customer = angular.copy(resp.data)
            })

            // add payment method here
            if ($scope.checkout.paymentMethod == true){
                this.paymentmethod = {
                    id: 1
                }
            }else {
                this.paymentmethod = {
                    id: 2
                }
            }
            // add address here
            if ( $scope.checkout.address.address == null) {
                this.customerPhoneAddress = {
                    id: $scope.address.id
                }
            }else {
                this.customerPhoneAddress = {
                    id: $scope.checkout.address.id
                }
            }
            // add coupon here
            if ($scope.checkout.couponItem.code == null){
                this.coupon = {
                    id: 1
                }
            } else {
                this.coupon = {
                    id: $scope.checkout.couponItem.id
                }
            }

        },
        get orderDetails(){
            return $scope.checkout.items.map(item =>{
                return {
                    food:{id: item.id},
                    newPrice: item.priceNew,
                    oldPrice: item.priceOld,
                    quantity: item.quantity,
                    status: 1,
                    display: true
                }
            })
        },
        purchase(){
            this.getUserInfo()
            let order = angular.copy(this)
            $http.post(`/rest/order/create`, order).then(resp => {
                console.log('order success: ', resp.data)
                // $scope.cart.clear()
                // location.href = "/order/detail/" + resp.data.id
            }).catch(error =>{
                console.log("Error", error)
            })
        }
    }

    //management cart
    $scope.cart = {
        items: [],
        quantity: 1,
        add(id) {
            let item = this.items.find(item => item.id == id)
            if (item) {
                item.quantity += this.quantity
                this.saveToLocalStorage();
            } else {
                this.createCart(id)
            }
        },
        addToSyn(id, quantity) {
            let item = this.items.find(item => item.id == id)
            if (item) {
                item.quantity = quantity
                this.saveToLocalStorage();
            } else {
                this.createCartToSyn(id, quantity)
            }
        },
        addToCart(id){
            let item = {
                quantity: 1,
                food:{id: id}
            }
            $http.post(`/rest/cart/create`, item).then(item => {
                $http.get(`/rest/imageFood/searchByFoodId/${id}`).then(res => {
                    $http.get(`/rest/food/searchById/${id}`).then(resp => {
                        resp.data.image = res.data.imageName
                        resp.data.quantity = 1
                        this.items.push(resp.data);
                        this.saveToLocalStorage();
                    })
                }).catch(err => console.log("err image", err))
            }).catch(err => $scope.error = 'Please try again!')
            this.liveToastBtn()
        },
        createCartToSyn(id, quantity) {
            $http.get(`/rest/imageFood/searchByFoodId/${id}`).then(res => {
                $http.get(`/rest/food/searchById/${id}`).then(resp => {
                    resp.data.image = res.data.imageName
                    resp.data.quantity = quantity
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }).catch(err => console.log("err image", err))
        },
        createCart(id) {
            $http.get(`/rest/imageFood/searchByFoodId/${id}`).then(res => {
                $http.get(`/rest/food/searchById/${id}`).then(resp => {
                    resp.data.image = res.data.imageName
                    resp.data.quantity = this.quantity
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }).catch(err => console.log("err image", err))
        },
        delete(id) {
            let index = this.items.findIndex(item => item.id == id)
            this.items.splice(index, 1)
            this.saveToLocalStorage()
            $scope.message = 'Delete Successfully delete 1!'
            this.liveToastBtn()
        },
        deleteToSyn(id) {
            let index = this.items.findIndex(item => item.id == id)
            this.items.splice(index, 1)
            this.saveToLocalStorage()
            $http.delete(`/rest/cart/delete/${id}`).then(resp =>{
                $scope.message = 'Delete Successfully!'
            }).catch(err => $scope.error = 'Please try delete again!')
            this.liveToastBtn()
        },
        synchronize(){
            let cart = angular.copy(this)
            $http.post("/rest/cart/synchronize", cart).then(resp => {
                $scope.message = "Your cart Synchronized"
            }).catch(error =>{
                console.log("Error", error)
            })
        },
        clear() {
            this.items = []
            this.saveToLocalStorage()
        },
        get cartInfo(){
            return $scope.cart.items.map(item =>{
                return {
                    food:{id: item.id},
                    quantity: item.quantity
                }
            })
        },
        get count(){
            return this.items
                .map(item => Number(item.quantity))
                .reduce((total, quantity) => total += quantity, 0)
        },
        get amount() {
            return this.items
                .map(item => item.quantity * item.price)
                .reduce((total, quantity) => total += quantity, 0)
        },
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
            this.synchronize()
        },
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart")
            this.items = json ? JSON.parse(json) : []
        },
        liveToastBtn(){
            let lastMessage = document.querySelector('.toast:last-child');
            new window.bootstrap.Toast(lastMessage).show();
        }
    }

    $scope.sleep = function(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
    $scope.addMinutesToDate = function (minutes) {
        let date = new Date();
        let newDate = new Date(date);
        newDate.setMinutes(date.getMinutes() + minutes);
        return newDate;
    }

    $scope.checkout.loadFromSessionStorage()

    $scope.cart.loadFromLocalStorage()
})

var compareTo = function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function (scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function (modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function () {
                ngModel.$validate();
            });
        }
    };
};

app.directive("compareTo", compareTo);