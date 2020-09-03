package com.lqw.business.mapper;

import com.lqw.business.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //根据条件查询所有角色
    public List<Role> selectRolelistByPage(Role role);

    //查询所有角色
    public List<Role> selectRoleList();

    //查询角色，自动补全
    public List<Role> selectRoleName(@Param("name") String name);

    //根据角色id获取对应的权限菜单id
    public List<String> selectRoleMenuidByRoleid(@Param("roleuuid") Integer roleuuid);

    //根据角色id获取对应的用户id
    public List<Integer> selectUseridByRoleuuid(@Param("roleuuid") Integer roleuuid);

    //更新角色信息
    public Integer updateRole(Role role);

    //添加角色
    public Integer insertRole(Role role);

    //新增角色权限菜单关系
    public void insertRolemenu(@Param("menuuuid") String menuuuid, @Param("roleuuid") Integer roleuuid);

    //根据id删除对应角色
    public Integer deleteRoleById(@Param("uuid") Integer uuid);

    //根据roleuuid删除拥有的角色信息
    public void deleteMenuidByRoleid(@Param("roleuuid") Integer roleuuid);

}
