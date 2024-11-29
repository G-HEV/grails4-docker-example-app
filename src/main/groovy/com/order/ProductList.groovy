package com.order

import grails.converters.JSON


class ProductList{

    private List list=[]
    ProductList(){

    }

    JSON toJSON(){
        return list as JSON;
    }
}
