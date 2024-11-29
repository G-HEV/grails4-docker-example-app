package com.order

class ProductController {

    ProductService productService;

    def index() {
        render "jestes w kontrolerze product"

    }

    def createProduct(ProductCO productCO) {
        if(productCO.validate()){
            //toDo implement add products

            render 200
        }
        response.sendError(400);
    }

    def productList() {

        ProductList productList = new ProductList();
        //todo implements generate fetch and generate productList

        render productList.toJSON();
    }

}
