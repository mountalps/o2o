package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {

    /**
     * query shop by its Id
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);


    /**
     * Add new shop
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * Update shop information
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
