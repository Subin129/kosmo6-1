package com.kosmo.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.onememo.service.OneMemoDTO;
import com.kosmo.onememo.service.OneMemoService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

@Repository
public class OneMemoDAO implements OneMemoService {

	@Resource(name = "template")
	private SqlSessionTemplate sqlMapper;

	@Override
	public boolean isLogin(Map map) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("memoIsLogin", map) == 0 ? false : true;
	}

//전체 목록용
	@Override
	public List<OneMemoDTO> selectLict(Map map) {

		return sqlMapper.selectList("memoSelectList", map);
	}

	@Override
	public int getTotalRecord(Map map) {
		return sqlMapper.selectOne("memoGetTotalRecord", map);
	}

	@Override
	public OneMemoDTO selectOne(Map map) {
		// TODO Auto-generated method stub
		 return sqlMapper.selectOne("memoSelectOne", map);
	}

	@Override
	public int delete(Map map) {
		// 프로그래밍적으로 자식 전부 삭제하고 부모삭제하거나 부모삭제시 자식도 삭제하도록 FK설정
		//REFERENCES 부모네이블(PK) ON DELETE CASCADE
		sqlMapper.delete("commentDeleteByNo", map);
		return sqlMapper.delete("memoDelete", map);
	}
//입력용
	@Override
	public int insert(Map map) {
		
		return sqlMapper.insert("memoInsert", map);
	}

	@Override
	public int update(Map map) {
		return sqlMapper.update("memoUpdate", map);
	}
	
	

}
