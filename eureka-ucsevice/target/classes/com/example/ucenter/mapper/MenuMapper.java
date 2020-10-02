package com.example.ucenter.mapper;

import com.example.entity.Menu;
import com.example.vo.MenuNodeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.Past;
import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("select * from menu where id = #{id}")
    Menu selectByPrimaryKey(@Param("id") int id);
    @Select("select * from menu")
    List<Menu> getMenuAll();
//    @Select("select * from menu where parentId = #{parentId}")
//    List<Menu> getMenuByParentId(@Param("parentId") int parentId);
}
