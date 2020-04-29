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
//서비스주입
	@Resource(name = "commentService")
	private MemoCommentService commentService;
	// 코멘트 입력처리

	@RequestMapping(value = "/BBS/Write.bbs", produces = "text/html; charset=UTF-8")
	@ResponseBody
	private String write(@RequestParam Map map, @ModelAttribute("id") String id) {

		map.put("id", id);
		commentService.insert(map);
		return map.get("no").toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/BBS/List.bbs", produces = "text/html; charset=UTF-8")
	public String list(@RequestParam Map map) {
		List<Map> list = commentService.selectList(map);
		System.out.println(JSONArray.toJSONString(list));
		
		for(Map comment : list)
			comment.put("CPOSTDATE", comment.get("CPOSTDATE").toString().subSequence(0, 10));
		
		return JSONArray.toJSONString(list);
	}
	//코멘트 수정처리
	@ResponseBody
	@RequestMapping(value = "/BBS/Edit.bbs", produces = "text/html; charset=UTF-8")
	public String update(@RequestParam Map map) {
		commentService.update(map);
		return map.get("cno").toString();
	}
	//코멘트 삭제처리
	@ResponseBody
	@RequestMapping(value = "/BBS/Delete.bbs", produces = "text/html; charset=UTF-8")
	public String delete(@RequestParam Map map) {
		commentService.delete(map);
		//삭제댓번호 밪ㄴ환
		return map.get("cno").toString();
	}
	

}
