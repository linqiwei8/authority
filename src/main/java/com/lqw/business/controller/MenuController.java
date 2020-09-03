package com.lqw.business.controller;

import com.lqw.business.pojo.Menu;
import com.lqw.business.pojo.User;
import com.lqw.business.service.MenuService;
import com.lqw.business.vo.GlobalResult;
import com.lqw.business.vo.Tree;
import com.lqw.core.annotation.LogAnno;
import com.lqw.core.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**   
 * @ClassName:  MenuController   
 * @Description: 菜单管理

 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	/**
	 * 查找所有
	 */
	@RequestMapping(value="/menulist")
	@ResponseBody
	public List<Tree> findAll() {
		return menuService.findMenuList(); 
	}
	
	/**
	 * 根据菜单id查找菜单，显示菜单详情
	 * @param menuid 主键
	 */
	@RequestMapping("/menufindById")
	@ResponseBody
	public List<Menu> findById(String menuid) {
		return menuService.findMenuById(menuid);
	}
	/**
	 * 添加数据
	 * @param menu 菜单对象
	 */
	@LogAnno(operateType = "添加权限菜单")
	@RequestMapping(value="/menuadd")
	@ResponseBody
	public GlobalResult insert(Menu menu) {
		return menuService.addMenu(menu);
	}
	
	/**
	 * 根据id删除数据[修改状态]
	 * @param menuid 主键
	 * @return
	 */
	@LogAnno(operateType = "修改权限菜单")
	@RequestMapping(value="/menudelete")
	@ResponseBody
	public GlobalResult deleteById(String menuid) {
		return menuService.deleteMenuById(menuid);
	}
	
	/**
	 * 根据id修改数据
	 * @param menu 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "更新权限菜单")
	@RequestMapping(value="/menuupdate")
	@ResponseBody
	public GlobalResult updateById(Menu menu) {
		return menuService.updateMenuById(menu);
	}
	
	
	/**
	 * 
	* @Title: loadMenu 
	* @Description: 根据session中的user_id加载菜单
	* @return Menu
	 */
	@RequestMapping(value="/loadMenus")
	@ResponseBody
	public Menu loadMenus() {
		User user = UserUtils.getSubjectUser();
		Menu menus = null;
		if(user!=null) {
			menus = menuService.findMenuByUserid(user.getUser_id());
		}
		return menus;
	}
}
