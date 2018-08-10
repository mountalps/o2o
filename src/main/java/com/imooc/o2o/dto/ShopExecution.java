package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Shop;

import java.util.List;

public class ShopExecution {

    //result state
    private int state;

    //state identifier
    private String stateInfo;

    //number of shops
    private int count;

    //which shop we are dealing with
    //used when adding, deleting or modifying shop
    private Shop shop;

    //list of shops
    //used when do SQL "select"
    private List<Shop> shopList;

    public ShopExecution(){

    }

    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

}
