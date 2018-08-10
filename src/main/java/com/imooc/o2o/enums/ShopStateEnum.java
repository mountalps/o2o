package com.imooc.o2o.enums;

import com.imooc.o2o.entity.Shop;

public enum ShopStateEnum {

    CHECK(0, "appending - 审核中"),
    OFFLINE(-1, "illegal - 非法店铺"),
    SUCCESS(1, "success - 操作成功"),
    PASS(2, "pass - 通过认证"),
    INNER_ERROR(-1001, "inner error - 内部系统错误"),
    NULL_SHOPID(-1002, "shop id is null - shopId为空");

    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * get corresponded enum according to the value of state
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum stateEnum: values()){
            if (stateEnum.getState() == state)
                return stateEnum;
        }

        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
