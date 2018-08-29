package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.ProductImg;

public interface ProductImgDao {

    /**
     *
     * list all the images of a specific product
     *
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     *
     * insert multiple images of a product
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     *
     * delete all the images of a product
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
