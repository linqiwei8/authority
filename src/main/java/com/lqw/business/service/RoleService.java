package com.lqw.business.service;

import com.lqw.business.pojo.Role;
import com.lqw.business.vo.EasyUIDataGridResult;
import com.lqw.business.vo.EasyUIOptionalTreeNode;
import com.lqw.business.vo.GlobalResult;

import java.util.List;

/**
 * @description: 处理角色相关业务
 * @author: linqiwei
 * @createDate: 2020/4/2
 * @version: 1.0
 */
public interface RoleService {

	//根据查询条件所有角色
	public EasyUIDataGridResult findRolelistByPage(Integer page, Integer rows, Role role);

	//查询所有可用的角色
	public EasyUIDataGridResult findRoleList();

	//like查询角色名
	public List<Role> findRoleName(String q);

	//更新角色的名称或者状态
	public GlobalResult updateRole(Role role);

	//添加角色
	public GlobalResult addRole(Role role);

	//根据uuid删除角色
	public GlobalResult deleteRoleById(Integer uuid);
	
	/**
	* @Title: findRoleMenu 
	* @Description: 获取角色菜单权限 
		-- 1.根据角色id获取对应的权限菜单id,比如角色id roleuuid=1
		select role_menu.menuuuid from role,role_menu WHERE role_menu.roleuuid=1;
		-- 2.获取所有权限菜单(menuid,menuname)
		SELECT menuid,menuname FROM menu;
	 */
	public List<EasyUIOptionalTreeNode> findRoleMenuByRoleid(Integer roleuuid);

	//更新角色权限菜单
	public GlobalResult updateRoleMenu(Integer roleuuid, String checkedIds);
}
