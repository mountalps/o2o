package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;
import java.io.InputStream;

public interface ShopService {


    /**
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return data list based on shopCondition
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * get shop's information by shop's id
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * modify shop's information
     * @param shop
     * @param thumbernail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbernail) throws ShopOperationException;

    /**
     * register a new shop, including picture processing
     * @param shop
     * @param thumbernail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbernail) throws ShopOperationException;


}
