package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * search shops by
     * shop's name(fuzzy search),
     * shop's condition
     * shop's category
     * areaid
     * owner
     * @param shopCondition
     * @param rowIndex: get data from which row
     * @param pageSize: number of rows per page
     * @return
     */
    List<Shop> queryShopList(
            @Param("shopCondition") Shop shopCondition,
            @Param("rowIndex") int rowIndex,
            @Param("pageSize") int pageSize
    );

    /**
     *
     * @param shopCondition
     * @return the number of queryShopList
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

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
