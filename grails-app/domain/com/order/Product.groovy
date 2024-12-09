package com.order

class Product {

    String name;
    Long price;


    static constraints = {
        name nullable: false, blank: false, size: 3..255, unique: true
        price nullable: false, min: 0L
    }

    Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }


}
