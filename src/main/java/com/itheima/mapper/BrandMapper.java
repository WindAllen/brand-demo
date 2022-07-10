package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    @ResultMap("BCresultMap")
    List<Brand> selectAll();
    void addBrand(Brand brand);

    Brand selectByid(int id);

    void updateBrand(Brand brand);
}
