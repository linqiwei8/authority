package com.lqw.business.service;


import com.lqw.business.pojo.Role;
import com.lqw.business.pojo.User;
import com.lqw.business.vo.EasyUIDataGridResult;
import com.lqw.business.vo.EasyUIOptionalTreeNode;
import com.lqw.business.vo.GlobalResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 
 * @ClassName:  UserService   
 * @Description: 处理用户相关业务
 */
public interface UserService {

	//根据账号和密码查找用户
	public User findUserByCodeAndPwd(String user_code, String user_pwd);

	//查询用户分页信息
	public EasyUIDataGridResult findUserlistByPage(User user, Integer page, Integer rows);

	//like查询用户名
	public List<User> findUserName(String q);

	/**
	 * @Title: findUserRole
	 * @Description: 查询所有角色，并设置选中的用户角色为true
	 * 				 -- 1.获取用户对应的角色,例如user_id=221 select uuid
	 *               from user_role,role where user_role.userid=221 and
	 *               user_role.roleuuid = role.uuid and role.enble=1;
	 *               -- 2.获取所有角色
	 *               select uuid,name from role where role.enble=1; --
	 *               -- 3.封装返回值将用户对应的角色设置为true,uuid、name
	 */
	List<EasyUIOptionalTreeNode> findUserRole(Integer user_id);

	//根据用户id查询对应的角色
	List<Role> findUserRoleByUserid(Integer user_id);

	//更新用户对应的角色
	GlobalResult updateUserRole(Integer user_id, String checkedIds);

	//添加用户
	public GlobalResult addUser(User user);

	//更新用户信息
	public GlobalResult updateUser(User user);

	//更新用户的密码
	public GlobalResult updatePwd(User user, String oldPwd, String newPwd);

	//删除用户
	public GlobalResult deleteUser(Integer user_id);

	//导出excel
	public void export(OutputStream os, User user);

	//导入excel
	public void doImport(InputStream is) throws IOException;
}
