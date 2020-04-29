package com.kosmo.onememo.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.onememo.service.OneMemoService;

@Controller
@RequestMapping("/OneMemo")
public class AuthController {
//서비스 주입
	@Resource(name = "memoService")
	private OneMemoService memooService;

	@RequestMapping("/Auth/Login.bbs")
	public String login() {

		return "member/Login.tiles";
	}
	
	@RequestMapping("/Auth/Logout.bbs")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "member/Login.tiles";
	}
	


	@RequestMapping("/Auth/LoginProcess.bbs")
	public String process(HttpSession session, @RequestParam Map map, Model model) {

		boolean flag = memooService.isLogin(map);

		if (flag)
			session.setAttribute("id", map.get("id"));
		else
			model.addAttribute("NotMember", "로그인 실패");

		return "member/Login.tiles";
	}
	
	
	@RequestMapping(value = "/Auth/IsLogin.bbs" , produces="text/html; charset=UTF-8")
	@ResponseBody
	//public String isLogin(HttpSession session) {
	public String isLogin(Authentication auth) {
		
		//스프링 시큐리티 적용시 인증되었다면 Authentication객체에 로그인과 관련된 정보가 전달됨
		//로그인이 안되어있으면 auth는 null
		JSONObject json = new JSONObject();
		
		if(auth==null) {
			json.put("IsLogin", "NO");
			return json.toJSONString();
		}
		
		//시큐리티 미 사용시
		/*if(session.getAttribute("id")==null) {
			json.put("IsLogin", "NO");
			return json.toJSONString();
		}
		*/
		json.put("IsLogin", "YES");
		return json.toJSONString();
	}
}
