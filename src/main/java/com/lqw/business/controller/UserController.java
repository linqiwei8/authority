package com.lqw.business.controller;
import com.lqw.business.pojo.Log;
import com.lqw.business.pojo.User;
import com.lqw.business.service.LogService;
import com.lqw.business.service.UserService;
import com.lqw.business.vo.EasyUIDataGridResult;
import com.lqw.business.vo.EasyUIOptionalTreeNode;
import com.lqw.business.vo.GlobalResult;
import com.lqw.core.annotation.LogAnno;
import com.lqw.core.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 * 
 * @ClassName: UserController
 * @Description: 用户相关操作请求接收器
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;

	/**
	 * 
	 * @Title: login
	 * @Description: 用户登录
	 * @param user_code
	 * @param user_pwd
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult login(String user_code, String user_pwd) {
		try {
			// 1.创建令牌
			UsernamePasswordToken token = new UsernamePasswordToken(user_code, user_pwd);
			// 2.获取主题subject
			Subject subject = SecurityUtils.getSubject();
			// 3.执行login方法
			subject.login(token);
			// 4.登录日志记录
			Log log = new Log();
			log.setOperatedate(new Date());
			log.setOperateor(user_code);
			log.setOperateresult("正常");
			log.setOperatetype("登录");
			log.setIp(UserUtils.getIpAddress());
			logService.addLog(log);
			return GlobalResult.build(200, "");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return GlobalResult.build(400, "账号或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			return GlobalResult.build(500, "服务端错误");
		}
	}

	/**
	 * 
	 * @Title: login
	 * @Description: 用户分页信息
	 * @param user 查询参数
	 * @param page 当前页
	 * @param rows 页面大小
	 * @return
	 */
	@RequestMapping(value = "/userlistByPage", method = RequestMethod.POST)
	@ResponseBody
	public EasyUIDataGridResult userlistByPage(User user,
											   @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
											   @RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) {
		return userService.findUserlistByPage(user, page, rows);
	}

	/**
	 * 
	 * @Title: searchUserName
	 * @Description: 用户名自动补全
	 * @param q
	 * @return
	 */
	@RequestMapping(value = "/searchUserName", method = RequestMethod.POST)
	@ResponseBody
	public List<User> searchUserName(String q) {
		List<User> userName = userService.findUserName(q);
		return userName;
	}

	/**
	 * 
	 * @Title: login
	 * @Description: 添加用户
	 * @param user 被添加的用户信息
	 * @return
	 */
	@LogAnno(operateType = "添加用户")
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult useradd(User user) {
		return userService.addUser(user);
	}

	/**
	 * 
	 * @Title: userupdate
	 * @Description: 更新用户信息
	 * @param user 被修改的用户信息
	 * @return
	 */
	@LogAnno(operateType = "更新用户")
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult userupdate(User user) {
		return userService.updateUser(user);
	}

	@LogAnno(operateType = "更新密码")
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult updatePwd(String oldPwd, String newPwd) {
		User user = UserUtils.getSubjectUser();
		GlobalResult result = userService.updatePwd(user, oldPwd, newPwd);
		// 密码修改完成后移除当前用户
		UserUtils.removeSubjectUser();
		return result;
	}

	/**
	 * 
	 * @Title: userdelete
	 * @Description: 根据user封装的条件删除用户
	 * @param user 封装的条件
	 * @return
	 */
	@LogAnno(operateType = "删除用户")
	@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult userdelete(User user) {
		return userService.deleteUser(user.getUser_id());
	}

	/**
	 * 
	 * @Title: findUserRole
	 * @Description: esayui tree默认POST方法
	 * @param user_id
	 * @return List<EasyUIOptionalTreeNode>
	 */
	@RequestMapping(value = "/findUserRole", method = { RequestMethod.POST })
	@ResponseBody
	public List<EasyUIOptionalTreeNode> findUserRole(@RequestParam(value = "id", required = true) Integer user_id) {
		List<EasyUIOptionalTreeNode> treeList = userService.findUserRole(user_id);
		return treeList;
	}

	/**
	 * 
	 * @Title: updateUserRole
	 * @Description: 更新用户对应的权限
	 * @param user_id
	 * @param checkedIds
	 * @return GlobalResult
	 */
	@LogAnno(operateType = "更新用户对应角色")
	@RequestMapping(value = "/updateUserRole", method = { RequestMethod.POST })
	@ResponseBody
	public GlobalResult updateUserRole(@RequestParam(value = "id", required = true) Integer user_id,
			@RequestParam(value = "checkedIds", required = true) String checkedIds) {
		GlobalResult result = userService.updateUserRole(user_id, checkedIds);
		return result;
	}

	/**
	 * 
	 * @Title: showName
	 * @Description: 显示用户名
	 * @return GlobalResult
	 */
	@RequestMapping(value = "/showName")
	@ResponseBody
	public GlobalResult showName() {
		GlobalResult result = null;
		if (UserUtils.getSubjectUser() == null) {
			result = GlobalResult.build(400, "用户未登录");
		} else {
			result = GlobalResult.build(200, UserUtils.getSubjectUser().getUser_name());
		}
		return result;
	}

	/**
	 * 
	 * @Title: logout
	 * @Description: 登出
	 * @return Map
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public String logout() {
		// 登录日志记录
		Log log = new Log();
		log.setOperatedate(new Date());
		log.setOperateor(UserUtils.getSubjectUser().getUser_code());
		log.setOperateresult("正常");
		log.setOperatetype("注销");
		log.setIp(UserUtils.getIpAddress());
		try {
			logService.addLog(log);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		UserUtils.removeSubjectUser();
		return null;
	}

	/**
	 * 
	 * @Title: userexport
	 * @Description: 根据user条件，导出对应的数据
	 * @param user 封装的条件
	 * @return
	 */
	@LogAnno(operateType = "导出用户信息Excel")
	@RequestMapping(value = "/userexport", method = RequestMethod.POST)
	@ResponseBody
	public void userexport(User user, HttpServletResponse response) {
		String filename = "Users_exportBy" + UserUtils.getSubjectUser().getUser_name() + ".xls";
		// 响应对象
		try {
			// 设置输出流,实现下载文件
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "ISO-8859-1"));

			userService.export(response.getOutputStream(), user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: userdoImport
	 * @Description: 导入用户信息excel
	 * @return
	 */
	@LogAnno(operateType = "导入用户信息Excel")
	@RequestMapping(value = "/userdoImport", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult userdoImport(MultipartFile file) {
		try {
			userService.doImport(file.getInputStream());
			return new GlobalResult(200, "文件上传成功", null);
		} catch (IOException e) {
			e.printStackTrace();
			return new GlobalResult(400, "文件上传失败", null);
		}
	}
}
