package com.lqw.business.mapper;

import com.lqw.business.pojo.Menu;
import com.lqw.business.vo.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    List<Tree> selectMenuList();

    List<Menu> selectMenuById(@Param("menuid") String menuid);

    Integer insertMenu(Menu Menu);

    Integer deleteMenuById(String menuid);

    Integer updateMenuById(Menu Menu);

    public List<Menu> selectMenuIdName(@Param("pid") String pid);

    public List<Menu> selectMenuByUserid(@Param("userid") Integer userid);

    public List<Menu> selectMenu(@Param("pid") String pid);

}
