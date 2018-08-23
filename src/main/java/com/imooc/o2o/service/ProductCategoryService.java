package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * get all the product categories of a specific shop
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);
}
