package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kosmo.onememo.service.MemoCommentService;

@Service("commentService")
public class MemoCommentServiceImpl implements MemoCommentService {
	//MemoCommentDAO주입]
	@Resource(name="commentDao")
	private MemoCommentDAO dao;
	
	@Override
	public List<Map> selectList(Map map) {
		
		return dao.selectList(map);
	}

	@Override
	public void insert(Map map) {
		dao.insert(map);		
	}

	@Override
	public void delete(Map map) {
		dao.delete(map);
		
	}

	@Override
	public void update(Map map) {
		dao.update(map);
		
	}

}
