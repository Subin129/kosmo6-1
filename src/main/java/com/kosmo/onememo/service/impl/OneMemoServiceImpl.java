package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kosmo.onememo.service.OneMemoDTO;
import com.kosmo.onememo.service.OneMemoService;


@Service("memoService")
public class OneMemoServiceImpl implements OneMemoService {
	
	@Resource(name="oneMemoDAO")
	private OneMemoDAO dao;
	

	@Override
	public boolean isLogin(Map map) {
		return dao.isLogin(map);
	}

	@Override
	public List<OneMemoDTO> selectLict(Map map) {
		
		return dao.selectLict(map);
	}

	@Override
	public int getTotalRecord(Map map) {
		// TODO Auto-generated method stub
		return dao.getTotalRecord(map);
	}

	@Override
	public OneMemoDTO selectOne(Map map) {
		// TODO Auto-generated method stub
		return dao.selectOne(map);
	}

	@Override
	public int delete(Map map) {
		// TODO Auto-generated method stub
		return dao.delete(map);
	}

	@Override
	public int insert(Map map) {
		// TODO Auto-generated method stub
		return dao.insert(map);
	}

	@Override
	public int update(Map map) {
		// TODO Auto-generated method stub
		return dao.update(map);
	}

}
