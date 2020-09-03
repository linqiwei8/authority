package com.lqw.business.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqw.business.mapper.LogMapper;
import com.lqw.business.pojo.Log;
import com.lqw.business.service.LogService;
import com.lqw.business.vo.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @ClassName: LogServiceImpl
 * @Description: 日志管理

 */
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public boolean addLog(Log log) throws SQLException {
		return logMapper.insertLog(log) > 0;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public EasyUIDataGridResult findLoglistByPage(Integer page, Integer rows, Log log) {
		PageHelper.startPage(page, rows);
		List<Log> list = logMapper.selectLoglistByPage(log);
		PageInfo<Log> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Log> findLogOperateor(String q) {
		return logMapper.selectLogOperateor(q);
	}

}