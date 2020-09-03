package com.lqw.business.service;

import com.lqw.business.pojo.Menu;
import com.lqw.business.vo.GlobalResult;
import com.lqw.business.vo.Tree;

import java.util.List;

/**
 * @description: 处理菜单相关业务
 * @author: linqiwei
 * @createDate: 2020/4/2
 * @version: 1.0
 */
public interface MenuService {

    //查找所有数据
    List<Tree> findMenuList();

    //根据菜单id查找菜单，显示菜单详情
    List<Menu> findMenuById(String menuid);

    //添加数据
    GlobalResult addMenu(Menu Menu);

    //根据id删除数据
    GlobalResult deleteMenuById(String menuid);

    //根据id修改数据
    GlobalResult updateMenuById(Menu Menu);

    //根据userid加载对应菜单
    Menu findMenuByUserid(Integer userid);

    //根据userid加载对应菜单无序列表
    List<Menu> findMenuListByUserid(Integer userid);

}
