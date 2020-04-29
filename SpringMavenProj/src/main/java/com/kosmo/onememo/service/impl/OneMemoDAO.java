package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.onememo.service.OneMemoDTO;
import com.kosmo.onememo.service.OneMemoService;

@Repository
public class OneMemoDAO implements OneMemoService {

	//SqlSessionTemplate객체 주입]
	@Resource(name="template")
	private SqlSessionTemplate sqlMapper;
	//로그인 용]
	@Override
	public boolean isLogin(Map map) {
		
		return (Integer)sqlMapper.selectOne("memoIsLogin",map)==0 ? false : true;
	}
	//전체 목록용]
	@Override
	public List<OneMemoDTO> selectList(Map map) {
		
		return sqlMapper.selectList("memoSelectList",map);
	}

	@Override
	public int getTotalRecord(Map map) {
		
		return sqlMapper.selectOne("memoGetTotalRecord",map);
	}
	//상세보기]
	@Override
	public OneMemoDTO selectOne(Map map) {		
		return sqlMapper.selectOne("memoSelectOne", map);
	}

	@Override
	public int delete(Map map) {
		/*메모 삭제-프로그래밍적으로 삭제하거나 혹은 
		   부모 삭제시 자동으로 해당 자식도 삭제되도록 FK설정
		  REFERENCES 부모테이블(PK컬럼) ON  DELETE CASCADE
		*/
		//자식삭제]
		sqlMapper.delete("commentDeleteByNo",map);
		//부모삭제]
		return sqlMapper.delete("memoDelete",map);
	}
	//입력용]
	@Override
	public int insert(Map map) {	
		System.out.println(map.get("id"));
		System.out.println(map.get("title"));
		System.out.println(map.get("content"));
		return sqlMapper.insert("memoInsert",map);
	}
	//수정]
	@Override
	public int update(Map map) {
		
		return sqlMapper.update("memoUpdate",map);
	}

}
