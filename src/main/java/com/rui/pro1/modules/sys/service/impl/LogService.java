package com.rui.pro1.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.SysLog;
import com.rui.pro1.modules.sys.mapper.SysLogMapper;
import com.rui.pro1.modules.sys.service.ILogService;

@Service
public class LogService implements ILogService {

	@Autowired
	SysLogMapper sysLogMapper; 
	
	@Override
	public QueryResult<SysLog> getList(Integer page, Integer pagesize, SysLog log) {
		
		
		Query query = new Query();
		query.setBean(log);
		query.setPageIndex(page);
		query.setPageSize(pagesize);

		// 组合分页信息
		QueryResult<SysLog> queryResult = new QueryResult<SysLog>();
		Long count = sysLogMapper.getCount(query);
		List<SysLog> list = sysLogMapper.queryPages(query);
		
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);
		return queryResult;
	}

	@Override
	public int add(SysLog log) {
		if(log==null){
			return 0;
		}
		
//		log.setCreateTime(new Date());
		
		sysLogMapper.insertSelective(log);
		
		return 0;
	}


}
