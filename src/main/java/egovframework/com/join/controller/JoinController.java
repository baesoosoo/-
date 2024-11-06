package egovframework.com.join.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.join.model.MemberDTO;
import egovframework.com.join.service.JoinService;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Controller
@RequestMapping("/uat/uia")
public class JoinController {
	
	
	@Autowired
	JoinService joinService;
	
	@GetMapping("/signup.do")
	public String join() {
		return "/main/join/memberJoin";
	}
	
	@PostMapping("/signupOk.do")
	public void joinOk(@RequestParam("mber_id") String mber_id,
            @RequestParam("password") String password,@RequestParam("mber_nm") String mber_nm,
            HttpServletResponse response) throws Exception {
		
		  response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      
	      System.out.println("mber_id"+mber_id);
	      System.out.println("password"+password);
	    
	      
	      String enpassword = EgovFileScrty.encryptPassword(mber_id,password);
	      
		
	      MemberDTO dto = new MemberDTO();
	      
	      dto.setMber_id(mber_id);
	      dto.setPassword(enpassword);
	      dto.setMber_nm(mber_nm);
	      
	      int check = joinService.insertMem(dto);
	      
	      if (check > 0) {

             out.println("<script>");
             out.println("alert('회원가입에 성공하였습니다.')");
             out.println("location.href='/sht_webapp/uat/uia/egovLoginUsr.do';");
             out.println("</script>");
          } else {
             out.println("<script>");
             out.println("alert('회원가입에 실패하였습니다.')");
             out.println("history.back()");
             out.println("</script>");
          }
	}
}
