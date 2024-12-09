package com.order

import grails.converters.JSON

class ProductController {

    ProductService productService;

    static allowedMethods = [createProduct: 'POST',
                             productList  : 'GET']

    def index() {
        render "jestes w kontrolerze product"

    }

    def createProduct(ProductCO productCO) {
        def result = productService.saveProduct(productCO)
        render result as JSON
    }

    def productList() {
        int offset = params.int('offset') ?: 0
        int max = params.int('max') ?: 0
        ProductList productList = productService.fetchProducts(offset, max)

        render productList.toJSON();
    }

}
