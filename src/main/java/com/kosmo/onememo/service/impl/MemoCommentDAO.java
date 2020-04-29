package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.onememo.service.MemoCommentService;

@Repository("commentDAO")
public class MemoCommentDAO implements MemoCommentService {


	@Resource(name = "template")
	private SqlSessionTemplate sqlMapper;
	
	@Override
	public List<Map> selectList(Map map) {
		// TODO Auto-generated method stub
		return 	sqlMapper.selectList("commentSelectList", map);
	}

	@Override
	public void insert(Map map) {
		sqlMapper.insert("commentInsert", map);
		
	}

	@Override
	public void delete(Map map) {
		sqlMapper.update("commentDelete", map);
	}

	@Override
	public void update(Map map) {
		sqlMapper.update("commentUpdate", map);
	}

}
