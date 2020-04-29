package com.kosmo.onememo.service;

import java.util.List;
import java.util.Map;

public interface OneMemoService {
	boolean isLogin(Map map);
	
	List<OneMemoDTO> selectLict(Map map);
	
	int getTotalRecord(Map map);
	
	OneMemoDTO selectOne(Map map);

	int delete(Map map);
	int insert(Map map);
	int update(Map map);
}
