const app = angular.module("foodStore-app", ['ngAnimate'])
app.controller("foodStore-controller", function ($scope, $http, $window) {
    $scope.items = []
    $scope.message = ''
    $scope.error = ''

    $scope.initialize = function (){
        $http.get(`/rest/food/getListFood`).then(resp => {
            $scope.items = resp.data
        })
        $http.get(`/rest/cart/getAll`).then(resp => {
            if (resp.data != null)
                for (let item of resp.data) {
                    $scope.cart.addToSyn(item.food.id, item.quantity)
                }
        })
    }

    $scope.initialize()

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