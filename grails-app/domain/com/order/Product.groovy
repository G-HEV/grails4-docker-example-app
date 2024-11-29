package com.order

class Product {

    String name;
    Long price;


    static constraints = {
        name nullable: true
        price nullable: true
    }

    Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }


}
