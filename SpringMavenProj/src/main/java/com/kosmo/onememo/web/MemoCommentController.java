package com.kosmo.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.onememo.service.MemoCommentService;

@SessionAttributes({"id"})
@Controller
@RequestMapping("/MemoComment")
public class MemoCommentController {
	//서비스 주입]
	@Resource(name="commentService")
	private MemoCommentService commentService;
	//코멘트 입력처리]
	@RequestMapping(value="/BBS/Write.bbs",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String write(@RequestParam Map map,@ModelAttribute("id") String id) {
		//서비스 호출]
		//한줄 댓글 작성자의 아이디 맵에 설정
		map.put("id",id);
		commentService.insert(map);
		//원본글의 번호 반환
		return map.get("no").toString();
	}////////
	//특정 글번호에 대한 코멘트 목록 가져오기
	@ResponseBody
	@RequestMapping(value="/BBS/List.bbs",produces = "text/html; charset=UTF-8")
	public String list(@RequestParam Map map) {
		//서비스 호출]
		List<Map> list=commentService.selectList(map);
		System.out.println(JSONArray.toJSONString(list));
		/*
		 * [
		 * {"NO":3,"CPOSTDATE":2019-09-26 15:20:42.0,
		 * "CNO":3,"LINECOMMENT":"첫번째 탯글입니다","ID":"LEE","NAME":"이길동"
		 * }]
		 * 
		 */
		//날짜 값을 문자열로 변경해야한다 그렇지 않으면 JSON형식에 맞지 않는다
		//날짜를 2018-09-12 10:15:38.0에서 "2018-09-12"형태로 변경
		for(Map comment:list)
			comment.put("CPOSTDATE",comment.get("CPOSTDATE").toString().substring(0, 10));
		//단,List에 저장된 객체는 반드시 Map이어야 한다
		return JSONArray.toJSONString(list);
	}
	//코멘트 수정처리]
	@RequestMapping(value="/BBS/Edit.bbs",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(@RequestParam Map map) {
		//서비스 호출]
		commentService.update(map);
		//원본글:댓글 번호 반환
		return String.format("%s:%s",
				map.get("no").toString(),map.get("cno").toString()); 
	}
	//코멘트 삭제처리]
	@RequestMapping(value="/BBS/Delete.bbs",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam Map map) {
		//서비스 호출]
		commentService.delete(map);
		//삭제 댓글 번호 반환
		return String.format("%s",map.get("cno").toString()); 
	}
	
}
