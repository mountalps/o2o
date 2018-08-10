package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;

import java.util.List;

public interface AreaDao {

    /**
     * return all the areas
     * @return
     */
    List<Area> queryArea();
}
