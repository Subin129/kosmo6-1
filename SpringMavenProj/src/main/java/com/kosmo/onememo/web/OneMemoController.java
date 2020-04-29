package com.kosmo.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.onememo.service.OneMemoDTO;
import com.kosmo.onememo.service.OneMemoService;
import com.kosmo.onememo.service.PagingUtil;

@SessionAttributes("id")
@RequestMapping("/OneMemo")
@Controller
public class OneMemoController {
	//서비스 주입]
	@Resource(name = "memoService")
	private OneMemoService memoService;
	
	
	//.tiles?파라미터 전달시 .tiles로 매핑이 안됨
	//아래는 @ModelAttribute("id") String id 사용시
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notSessionAttribute(Model model) {
		model.addAttribute("NotMember", "로그인후 이용하세요");
		//로그인이 안된경우 로그인 페이지로
		return "member/Login.tiles";
	}
	//리소스파일(resource.properties)에서 읽어오기
	@Value("${PAGE_SIZE}")
	private int pageSize;
	@Value("${BLOCK_PAGE}")
	private int blockPage;
	
	//목록 처리]
	@RequestMapping("/BBS/List.bbs")
	public String list(
			//@ModelAttribute("id") String id,//세션영역에서 id가져오기-isLogin.jsp파일 사용시 불필요
			@RequestParam Map map,
			@RequestParam(required = false,defaultValue = "1") int nowPage,
			HttpServletRequest req,//컨텍스트 루트 얻기용
			Model model) {
		//서비스 호출]
		//페이징을 위한 로직 시작]
		//전체 레코드수	
		int totalRecordCount = memoService.getTotalRecord(map);
		//전체 페이지수]
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		//시작 및 끝 ROWNUM구하기]
		int start = (nowPage-1)*pageSize+1;
		int end   = nowPage*pageSize;	
		//페이징을 위한 로직 끝]	
		map.put("start", start);
		map.put("end", end);
		
		List<OneMemoDTO> list=memoService.selectList(map);
		//데이타 저장]
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize,blockPage, nowPage, req.getContextPath()+"/OneMemo/BBS/List.bbs?");
		
		model.addAttribute("list", list);
		model.addAttribute("pagingString", pagingString);
		//뷰정보 반환]
		return "bbs/List.tiles";
	}///////////////
	//입력폼으로 이동]	
	@RequestMapping(value="/BBS/Write.bbs",method=RequestMethod.GET)
	public String write(
			@ModelAttribute("id") String id//로그인하지 않는채로 write.bbs URL로 직접 접근시를 위한 매개변수
			                               //(Write.jsp에서 IsLogin.jsp를 인클루드 하지 않는 경우)
			) {
		//뷰정보 반환]
		return "bbs/Write.tiles";
	}
	//입력처리]	
	@RequestMapping(value="/BBS/Write.bbs",method=RequestMethod.POST)
	public String writeOk(
			@ModelAttribute("id") String id,
			@RequestParam Map map
			) {
		//서비스 호출]
		//호출전 아이디 맵에 저장
		map.put("id",id);
		memoService.insert(map);
		//뷰정보 반환:목록으로 이동
		return "forward:/OneMemo/BBS/List.bbs";
	}///////////
	//상세보기]
	@RequestMapping("/BBS/View.bbs")
	public String view(@RequestParam Map map,Model model) {
		//서비스 호출]
		OneMemoDTO record=memoService.selectOne(map);
		//데이타 저장]
		//줄바꿈 처리
		record.setContent(record.getContent().replace("\r\n", "<br/>"));
		model.addAttribute("record", record);
		//뷰정보 반환:
		return "bbs/View.tiles";
	}/////////////
	//수정폼으로 이동 및 수정처리]
	@RequestMapping("/BBS/Edit.bbs")
	public String edit(HttpServletRequest req,@RequestParam Map map) {
		if(req.getMethod().equals("GET")) {//수정폼으로 이동
			//서비스 호출]
			OneMemoDTO record=memoService.selectOne(map);
			//데이타 저장]
			req.setAttribute("record", record);
			//수정 폼으로 이동]
			return "bbs/Edit.tiles";
		}
		//수정처리후 상세보기로 이동
		//서비스 호출
		memoService.update(map);
		//뷰로 포워드
		return "forward:/OneMemo/BBS/View.bbs";
	}//////////////edit
	//삭제처리]
	@RequestMapping("/BBS/Delete.bbs")
	public String delete(@RequestParam Map map) {
		//서비스 호출
		memoService.delete(map);
		//뷰정보 반환]
		return "forward:/OneMemo/BBS/List.bbs";
	}
	
	
}
