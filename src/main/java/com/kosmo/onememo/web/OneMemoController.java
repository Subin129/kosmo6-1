package com.kosmo.onememo.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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





//@SessionAttributes("id")
@RequestMapping("/OneMemo")
@Controller
public class OneMemoController {
	//서비스 주입
	@Resource(name = "memoService")
	private OneMemoService memoService;
	
	
	
	
	
	
	//예외처리(로그인안된거) => .tiles?**=@@ 파라미터 전달시 tiles로 매핑이 안됨
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notSessionAttribute(HttpSessionRequiredException e) {
		return "member/Login.tiles";
	}
	
	//리소스파일(resource.properties)에서 읽어오기
		@Value("${PAGE_SIZE}")
		private int pageSize;
		@Value("${BLOCK_PAGE}")
		private int blockPage;
		
	//목록 처리
	@RequestMapping("/BBS/List.bbs")
	public String list2(//@ModelAttribute("id") String id , - 세션영역에서 id가져오기, isLogin으로 해서 필요없음
			@RequestParam Map map, Model model, @RequestParam(required = false,defaultValue = "1") int nowPage,
			HttpServletRequest req) {//컨텍스트 루트 얻기용)
		
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
		
		List<OneMemoDTO> list =  memoService.selectLict(map);
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()+"/OneMemo/BBS/List.bbs?");

		model.addAttribute("list", list);
		model.addAttribute("pagingString", pagingString);
		return "BBS/List.tiles";
	}
	
	@RequestMapping(value = "/BBS/Write.bbs", method = RequestMethod.GET)
	public String write(
			//@ModelAttribute("id") String id 세션어트리뷰트에서 아이디 얻어올때
			Authentication auth) {
		
		String id = ((UserDetails)auth.getPrincipal()).getUsername(); // 시큐리티 지정시
		
		return "BBS/Write.tiles";
	}
	
	
	@RequestMapping(value = "/BBS/Write.bbs", method = RequestMethod.POST)
	public String writeOK(
			// @ModelAttribute("id") String id, 시큐리티 미 사용시
			Authentication auth, //스프링 시큐리티 사용시 무조건 auth
			@RequestParam Map map) {
		//로그인 안된경우 auth는 null
		System.out.println("[Authentication 객체 출력] "+auth);
		
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		System.out.println("로그인한 사용자의 권한  : ");
		Collection authorities = userDetails.getAuthorities();
		for(Object authority : authorities ) {
			System.out.println(((GrantedAuthority)authority).getAuthority());
		}
		String id = userDetails.getUsername();
		System.out.println(id);
		
		System.out.println(userDetails.getPassword());
		
			map.put("id", id);
			memoService.insert(map);
			
		return "forward:/OneMemo/BBS/List.bbs";
	}
	//상세보기
		@RequestMapping("/BBS/View.bbs")
		public String view(@RequestParam Map map, Model model) {
			OneMemoDTO record= memoService.selectOne(map);
			record.setContent(record.getContent().replace("\r\n", "</br>"));
			
			model.addAttribute("record", record);
			
			
		return "BBS/View.tiles";	
		
			
		}
		
		@RequestMapping("/BBS/Edit.bbs")
		public String edit(@RequestParam Map map, Model model) {
			OneMemoDTO record= memoService.selectOne(map);
			record.setContent(record.getContent().replace("\r\n", "</br>"));
			
			model.addAttribute("record", record);
			
			
		return "BBS/Edit.tiles";
		
			
		}
		@RequestMapping("/BBS/EditOK.bbs")
		public String editok(@RequestParam Map map, Model model) {
			memoService.update(map);
			
			
			
		return "forward:/OneMemo/BBS/View.bbs?no="+map.get("no");
		}
		
		@RequestMapping("/BBS/Delete.bbs")
		public String delete(@RequestParam Map map) {
			memoService.delete(map);
			
			
			
		return "forward:/OneMemo/BBS/List.bbs";
		}
		
		
	
	
	
}
