package com.example.ucenter.converter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.Menu;
import com.example.vo.MenuNodeVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuConverter {
    public static List<MenuNodeVo> converterToMenuNodeVo(List<Menu> menus){
        //过滤出用户的菜单
        List<MenuNodeVo> menuNodeVos = new ArrayList<>();
        if(!CollectionUtil.isEmpty(menus)){
            for(Menu menu : menus){
                if(menu.getType()=='0'){
                    MenuNodeVo menuNodeVo = new MenuNodeVo();
                    BeanUtils.copyProperties(menu,menuNodeVo);
                    menuNodeVo.setDisabled(menu.getAvailable()==0);
                    menuNodeVos.add(menuNodeVo);
                }
            }
        }
        return menuNodeVos;
    }
}
