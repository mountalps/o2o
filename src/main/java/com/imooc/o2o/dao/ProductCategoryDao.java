package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * get product categories by shop's id
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * insert multiple product categories
     * @param productCategories
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategories);
}
