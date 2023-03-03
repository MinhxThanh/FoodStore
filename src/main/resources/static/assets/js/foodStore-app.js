const app = angular.module("foodStore-app", ['ngAnimate'])
app.controller("foodStore-controller", function ($scope, $http) {

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
        createCart(id) {
            $http.get(`/rest/imageFood/searchByFoodId/${id}`).then(resp => {
                $scope.image = resp.data.imageName
                console.log("image success", resp.data)
            }).catch(err => console.log("err image", err))

            $http.get(`/rest/food/searchById/${id}`).then(resp => {

                resp.data.image = $scope.image
                resp.data.quantity = this.quantity
                this.items.push(resp.data);
                this.saveToLocalStorage();
                console.log(this)
            })
        }
        ,
        delete(id) {
            let index = this.items.findIndex(item => item.id == id)
            this.items.splice(index, 1)
            this.saveToLocalStorage()
        },
        clear() {
            this.items = []
            this.saveToLocalStorage()
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
        },
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart")
            this.items = json ? JSON.parse(json) : []
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