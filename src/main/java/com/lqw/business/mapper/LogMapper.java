package com.lqw.business.mapper;

import com.lqw.business.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {

	//向数据库添加日志
	int insertLog(Log log);

	//查询
	public List<Log> selectLoglistByPage(Log log);

	//模糊查询操作人
	public List<Log> selectLogOperateor(@Param("operateor") String operateor);

}
