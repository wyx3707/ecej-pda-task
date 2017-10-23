package com.ecej.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ecej.nove.dao.base.BaseDao;

@Repository
public class TaskBaseDao extends BaseDao {

	@Resource
	public void setTaskSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
