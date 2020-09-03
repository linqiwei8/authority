package com.lqw.business.service;

import com.lqw.business.pojo.Log;
import com.lqw.business.vo.EasyUIDataGridResult;

import java.sql.SQLException;
import java.util.List;

/**
 * @description: 处理日志相关业务
 * @author: linqiwei
 * @createDate: 2020/4/2
 * @version: 1.0
 */
public interface LogService {

    //增加日志
    public boolean addLog(Log log) throws SQLException;

	//查询日志
	public EasyUIDataGridResult findLoglistByPage(Integer page, Integer rows, Log log);

	//模糊查询用户名
	public List<Log> findLogOperateor(String q);
}