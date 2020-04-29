package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.onememo.service.MemoCommentService;

@Repository("commentDao")
public class MemoCommentDAO implements MemoCommentService {

	//SqlSessionTemplate객체 주입]
	@Resource(name="template")
	private SqlSessionTemplate sqlMapper;
	//현재 글번에 따른 모든 댓글 목록]
	@Override
	public List<Map> selectList(Map map) {
		
		return sqlMapper.selectList("commentSelectList", map);
	}

	@Override
	public void insert(Map map) {
		sqlMapper.insert("commentInsert",map);		
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
