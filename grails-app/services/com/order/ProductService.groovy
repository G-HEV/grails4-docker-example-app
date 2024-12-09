package com.order

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class ProductService {

    @Transactional
    def saveProduct(ProductCO productCO) {
        if (!productCO.validate()) {
            return productCO.errors
        }

        def product = new Product(name: productCO.name, price: productCO.price)
        return product.save(flush: true) ? product : product.errors
    }

    @ReadOnly
    ProductList fetchProducts(int offset, int max) {
        def productList = Product.createCriteria().list(max: max, offset: offset) {
            order("name", "asc")
        } as List<Product>

        ProductList result = new ProductList()
        result.list = productList.collect { product ->
            [
                    name : product.name,
                    price: product.price
            ]
        }
        return result
    }
}
