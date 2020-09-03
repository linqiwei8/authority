package com.lqw.business.controller;


import com.lqw.business.pojo.Log;
import com.lqw.business.service.LogService;
import com.lqw.business.vo.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 
 * @ClassName:  LogController   
 * @Description: 日志
 */
@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	private LogService logService;
	
	/**
	 * 
	 * @Title: rolelistByPage   
	 * @Description: 返回分页日志
	 * @param log
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/loglistByPage")
	@ResponseBody
	public EasyUIDataGridResult rolelistByPage(Log log,
											   @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
											   @RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) {
		EasyUIDataGridResult result = logService.findLoglistByPage(page, rows, log);
		return result;
	}
	
	/**
	 * 
	 * @Title: searchLogOperateor   
	 * @Description: 模糊查询用户名
	 * @return
	 */
	@RequestMapping(value = "/searchLogOperateor", method = RequestMethod.POST)
	@ResponseBody
	public List<Log> searchLogOperateor(String q) {
		List<Log> operateor = logService.findLogOperateor(q);
		return operateor;
	}
	
	
}
