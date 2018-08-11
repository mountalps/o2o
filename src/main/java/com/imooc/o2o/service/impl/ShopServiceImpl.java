package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {

        //check whether shop is null
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            //initiate shop
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            //insert this shop
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0){
                throw new ShopOperationException("creating shop fails");
            }else {
                if (shopImg != null){
                    //save this image
                    try {
                        addShopImg(shop, shopImg);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error: "+e.getMessage());
                    }

                    //update picture address of the shop
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0){
                        throw new ShopOperationException("updating picture address fails");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error" + e.getMessage());
        }

        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, File shopImg) {
        //get the shop's directory
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);

    }

}
