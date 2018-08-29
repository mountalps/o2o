package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Product;

public interface ProductDao {

    /**
     * query products list and spit to pages
     * can use the following conditions to search:
     * product name, production state, shop id, product category
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**

     * query the total number of products
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * query product info according to producId
     *
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * insert product
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * update product info
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * set product's category id to null before deleting product category
     *
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

    /**
     * delete product
     *
     * @param productId
     * @return
     */
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
