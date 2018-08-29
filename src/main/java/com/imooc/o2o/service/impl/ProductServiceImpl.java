package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    // 1. Deal with thumbnail, get the relative path and assign it to product
    // 2. write the product info to tb_product, get productId
    // 3. deal with product thumbnails according to productId
    // 4. insert thumbnails to tb_product_img
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException {
        // check null
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // assign default attributes to product
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // default status: 1(on the shelf)
            product.setEnableStatus(1);
            // if thumbnail isn't null, add it
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                // create product info
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("product creation fails");
                }
            } catch (Exception e) {
                throw new ProductOperationException("product creation fails: " + e.toString());
            }
            // if product image isn't null
            if (productImgHolderList != null && productImgHolderList.size() > 0) {
                addProductImgList(product, productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            // 传参为空则返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * add thumbnail
     *
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * add batch of images
     *
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        // get the image's path, save it to its shop's folder
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        // traverse all pictures, and add them into productImg class
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        // if there are images to add, do batch insert
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("Product's images creation fails");
                }
            } catch (Exception e) {
                throw new ProductOperationException("Product's images creation fails: " + e.toString());
            }
        }
    }
}
