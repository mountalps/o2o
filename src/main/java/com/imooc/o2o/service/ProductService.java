package com.imooc.o2o.service;


import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {


    /**
     * 添加商品信息以及图片处理
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;


}
