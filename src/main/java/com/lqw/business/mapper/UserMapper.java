package com.lqw.business.mapper;


import com.lqw.business.pojo.Role;
import com.lqw.business.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //根据user_code和user_pwd查询user表
    public User selectUserBycodeAndpwd(@Param("user_code") String user_code,
                                       @Param("user_pwd") String user_pwd);

    //查询所有用户信息
    public List<User> selectUserlistByPage(User user);

    //查询用户姓名，自动补全
    public List<User> selectUserName(@Param("user_name") String user_name);

    //根据userid获取用户对应的角色,例如user_id=221
    public List<Role> selectUserRole(@Param("user_id") Integer user_id);

    //证据用户账号查询对应用户
    public List<User> selectUserByUserCode(String user_code);

    //添加用户
    public Integer insertUser(User user);

    //给用户添加角色
    public void insertUserRole(@Param("user_id") Integer user_id, @Param("roleuuid") Integer roleuuid);

    //更新用户信息
    public Integer updateUser(User user);

    //更新密码
    public Integer updatePwdById(@Param("user_id") Integer user_id, @Param("user_pwd") String user_pwd);

    //更新用户信息根据用户账号
    public Integer updateUserByUserCode(User user);

    //根据主键删除用户信息
    public Integer deleteUserById(@Param("user_id") Integer user_id);

    //删除用户关联的角色id
    public void deleteUserRole(@Param("user_id") Integer user_id);

}
