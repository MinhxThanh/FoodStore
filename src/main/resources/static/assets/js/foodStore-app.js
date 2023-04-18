const app = angular.module("foodStore-app", ['ngAnimate', 'ngSanitize'])
app.controller("foodStore-controller", function ($scope, $http, $window,$timeout) {
    $scope.items = []
    $scope.address = []
    $scope.customer = {}
    $scope.address = {}
    $scope.form = {}
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
        $http.get(`/rest/customerPhoneAddress/findAllByCustomerEmail`).then(resp => {
            $scope.address = angular.copy(resp.data)
        })
        //get profile customer
		$http.get(`/rest/customerProfile/findByCustomerEmail`).then(resp => {
			resp.data.birthday = new Date(resp.data.birthday)
            $scope.customer = angular.copy(resp.data)
        })
    }

    $scope.initialize()
	
	//phan trang san pham
	$scope.pager = {
		page:0,
		size:6,
		get items(){
			var start = this.page * this.size;
			return	$scope.items.slice(start,start+this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.items.length/this.size);
		},
		first(){
			this.page = 0;
		},
		previous(){
			this.page--;
			if(this.page<0){
				this.last();
			};
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			};
		},
		last(){
			this.page = this.count -1;
		},
	}
	
	//cap nhat profile customer
	$scope.update = function () {
        var item = angular.copy($scope.customer);
        $http.put('http://localhost:8080/rest/customerProfile/update/' + item.email, item).then(resp => {
			$scope.message = "Update profile successfully!"
			$scope.cart.liveToastBtn()
        }).catch(error =>{
			$scope.error = "Error update profile!"
			$scope.cart.liveToastBtn()
            console.log("Error", error)
        })
    }
    
    //them dia chi
    $scope.addAddress = function () {
		let item = {
		id: $scope.form.id,
		customer: $scope.customer,
		 username: $scope.form.username,
		 phone: $scope.form.phone,
		 cityProvince: $scope.form.cityProvince,
		 address: $scope.form.address,
		 default: $scope.form.default
	 	}
	 	
        $http.post(`/rest/customerPhoneAddress/create`, item).then(resp => {
			$scope.address.push(resp.data)
		let index = $scope.address.findIndex(item => item.id == resp.data.id)
        $scope.address[index] = item
			$scope.message = "Add contact successfully!"
			$scope.cart.liveToastBtn()
        }).catch(error =>{
           $scope.error = "Error create contact!"
           $scope.cart.liveToastBtn()
            console.log("Error create", error)
        })
        $timeout(function() {
		  location.reload();
		}, 3000);
    }
    
    $scope.addModel = function () {
        $scope.add = true;
        $scope.update = false;
        $('#addressModal').modal('show');
    }
    $scope.back = function () {
        $scope.form = {
		 customer: null,
		 username: null,
		 phone: null,
		 cityProvince: null,
		 address: null
	 	}
    }
    
    //do dia chi len form
     $scope.updateModel2 = function (list){
		$scope.add = false;
        $scope.update = true;
		$scope.form = {
		 id: list.id,
		 default: list.default,
		 customer: list.customer,
		 username: list.username,
		 phone: list.phone,
		 cityProvince: list.cityProvince,
		 address: list.address
	 }
        $('#addressModal').modal('show');
	}
	
	//cap nhat dia chi
    $scope.updateAddress = function () {
        var item = angular.copy($scope.form)
        $http.put(`/rest/customerPhoneAddress/update/${item.id}`, item).then(resp => {
			let index = $scope.address.findIndex(item => item.id == resp.data.id)
            $scope.address[index] = item
            $scope.message = "Update address successfully!"
			$scope.cart.liveToastBtn()
        }).catch(err =>{
           $scope.error = "Error update contact!"
           $scope.cart.liveToastBtn()
            console.log("Error updateAddress: ", err)
        })
        $timeout(function() {
		  location.reload();
		}, 3000);
    }
    
    //confirm xoa dia chi
    $scope.confirmRemove = function (list) {
		 $scope.del=angular.copy(list)
    	 $('#addressDel').modal('show');
    }
    //xoa dia chi
    $scope.delAddress = function () {
		let list = $scope.del
		console.log("list delete: ", list.id)
		$http.delete(`/rest/customerPhoneAddress/delete/${list.id}`).then(resp => {
				let index = $scope.address.findIndex(item => item.id == resp.data.id)
            $scope.address.splice(index, 1)
            $scope.message = "Delete address successfully!"
			$scope.cart.liveToastBtn()
	        }).catch(error =>{
	            $scope.message = "Error delete address!"
				$scope.cart.liveToastBtn()
	            console.log("Error delete", error)
	        })
	        list = null
	        $timeout(function() {
			  location.reload();
			}, 3000);
	}
    // comment in food detail
    $scope.commentFood = {
        items: [],
        review: {},
        customer: {},
        getInfo(foodId){
            $http.get(`/rest/commentFood/getAll`).then(resp =>{
                this.items = angular.copy(resp.data)
                this.items.forEach(item => {
                    let date = new Date(item.createDate)
                    item.createDate = moment(date).fromNow();
                })
            })
            $http.get(`/rest/customer/findCustomerIsPresent`).then(resp =>{
                this.customer = resp.data
                if (this.customer.email != null) {
                    $http.get(`/rest/commentFood/getInfoReviewOfCustomer/${foodId}/${this.customer.email}`).then(resp =>{
                        this.review = resp.data
                    })
                }
            })

        },
        create(){
            let item = {
                content : tinymce.get("commentFood").getContent(),
                review: this.review,
                createDate: new Date(),
                isDisplay: true,
                title: this.customer.lastName + ' ' + this.customer.firstName,
                updateDate: new Date()
            }
            $http.post(`/rest/commentFood/create`, item).then(resp =>{
                resp.data.createDate = moment(new Date(resp.data.createDate)).fromNow();
                this.items.push(resp.data)
                tinymce.get("commentFood").setContent("<p></p>");
                $scope.message = "Create comment successfully!"
                $scope.cart.liveToastBtn()
            })
        },
        delete(id){
            $http.delete(`/rest/commentFood/delete/${id}`).then(resp =>{
                let index = this.items.findIndex(item => item.id == id)
                this.items.splice(index, 1)
                $scope.message = "Delete reply comment successfully!"
                $scope.cart.liveToastBtn()
            })
        }
    }

    // comment in blog detail
    $scope.commentBlog = {
        blogId: 'this is content',
        comments: [],
        customer: {},
        commentId: '',
        commentReply: [],
        getCommentInfo(blogId){
            this.blogId = blogId
            $http.get(`/rest/commentBlog/findByBlogId/${blogId}`).then(resp =>{
                this.comments = resp.data
                this.comments.forEach(item => {
                    let date = new Date(item.createDate)
                    item.createDate = moment(date).fromNow();
                })
            })
            $http.get(`/rest/commentReplyBlog/findAllByBlogId/${blogId}`).then(resp =>{
                this.commentReply = resp.data
                this.commentReply.forEach(item => {
                    let date = new Date(item.createDate)
                    item.createDate = moment(date).fromNow();
                })
            })
            $http.get(`/rest/customer/findCustomerIsPresent`).then(resp =>{
                this.customer = resp.data
            })
        },
        create(){
            let item = {
                content : tinymce.get("myTextarea").getContent(),
                blogId: this.blogId
            }
            console.log("item comment: ", item)
            $http.post(`/rest/commentBlog/create`, item).then(resp =>{
                resp.data.createDate = moment(new Date(resp.data.createDate)).fromNow();
                this.comments.push(resp.data)
                tinymce.get("myTextarea").setContent("<p></p>");
                $scope.message = "Create comment successfully!"
                $scope.cart.liveToastBtn()
            })
        },
        delete(id){
            $http.delete(`/rest/commentBlog/delete/${id}`).then(resp =>{
                let index = this.comments.findIndex(item => item.id == id)
                this.comments.splice(index, 1)
                $scope.message = "Delete comment successfully!"
                $scope.cart.liveToastBtn()
            })
        },
        createCommentReply(id) {
            let item = {
                content : tinymce.get("myTextarea-" + this.commentId).getContent(),
                blogId: this.blogId,
                commentId: id
            }
            $http.post(`/rest/commentReplyBlog/create`, item).then(resp =>{
                resp.data.createDate = moment(new Date(resp.data.createDate)).fromNow();
                this.commentReply.push(resp.data)
                $scope.message = "Create reply comment successfully!"
                $scope.cart.liveToastBtn()
            })
            console.log("reply: ", item)
        },
        delete(id){
            $http.delete(`/rest/commentReplyBlog/delete/${id}`).then(resp =>{
                let index = this.commentReply.findIndex(item => item.id == id)
                this.commentReply.splice(index, 1)
                $scope.message = "Delete reply comment successfully!"
                $scope.cart.liveToastBtn()
            })
        },
        displayForm(id) {
            this.commentId = id
            tinymce.init({
                selector: `#myTextarea-${id}`,
                menubar: false,
                toolbar_location: "bottom",
                plugins: "autoresize link lists emoticons image",
                autoresize_bottom_margin: 50,
                max_height: 500,
                placeholder: "Enter message. . .",
                toolbar:
                    "bold italic strikethrough link numlist bullist blockquote emoticons image"
            });

            document.getElementById("form-" + id).style.display = "block";
        },
        deleteForm(id) {
            document.getElementById("form-" + id).style.display = "none";
        },
        displayFormReply(id) {
            this.commentId = id
            tinymce.init({
                selector: `#myTextareaReply-${id}`,
                menubar: false,
                toolbar_location: "bottom",
                plugins: "autoresize link lists emoticons image",
                autoresize_bottom_margin: 50,
                max_height: 500,
                placeholder: "Enter message. . .",
                toolbar:
                    "bold italic strikethrough link numlist bullist blockquote emoticons image"
            });

            document.getElementById("formReply-" + id).style.display = "block";
        },
        deleteFormReply(id) {
            document.getElementById("formReply-" + id).style.display = "none";
        }
    }


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

    $scope.checkout.loadFromSessionStorage()



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