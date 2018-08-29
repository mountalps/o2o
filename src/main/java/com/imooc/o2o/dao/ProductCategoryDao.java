package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

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

    /**
     * to delete a specific product category
     * @param productCategoryId
     * @param shopId
     * @return effectNum
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
