package com.example.utils;

import com.example.vo.MenuNodeVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MenuTreeBuilder {

    public static List<MenuNodeVo> build(List<MenuNodeVo> nodes){
        List<MenuNodeVo> rootMenu = new ArrayList<>();
        for(MenuNodeVo menuNodeVo : nodes){
            if(menuNodeVo.getParentId() == 0){
                rootMenu.add(menuNodeVo);
            }
        }
        Collections.sort(rootMenu,MenuNodeVo.order());
        for(MenuNodeVo nav : rootMenu){
            List<MenuNodeVo> childList = getChild(nav.getId(),nodes);
            nav.setChildren(childList);
        }
        return rootMenu;

    }
    private  static List<MenuNodeVo> getChild(int id,List<MenuNodeVo> nodes) {
        List<MenuNodeVo> childList = new ArrayList<>();
        for (MenuNodeVo nav : nodes) {
            if (nav.getParentId() == id) {
                childList.add(nav);
            }
        }

        for (MenuNodeVo nodeVo : childList) {
            nodeVo.setChildren(getChild(nodeVo.getId(), nodes));
        }


        Collections.sort(childList, MenuNodeVo.order());
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }

}
