package com.kosmo.onememo.service;

import java.util.List;
import java.util.Map;

public interface MemoCommentService {
	//목록용, MAP에 담아봄(원레DTO)
	
	List<Map> selectList(Map map);

	void insert(Map map);
	void delete(Map map);
	void update(Map map);

}
