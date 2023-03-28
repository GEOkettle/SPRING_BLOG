package com.geo.blog.test;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {
	
	@GetMapping("/")
	public String landingPage() {
	
		
		return "<p style='font-size:2rem;font-weight:800;'>나는 쿼리를테스트 해볼 수 있는 디비 시각화 툴을 만들고 싶어</p>";
	}
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member nm = Member.builder().email("geo@codefarm.com").password("1234").userName("geo").build();
		System.out.println("before set id: "+nm.getId());
		nm.setId(1);
		System.out.println("after set id: "+nm.getId());
		
		return "lombok test complete";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m  ) {//기본자료형 param은 @RequestParam ,member는 같은패키지에 정의한 클래스
		return "get req id:  "+ m.getId()+" userName: " + m.getUserName()+" password: " + m.getPassword()
		+ " email: " + m.getEmail();
	}
		
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {//Message Converter가 들어온 json을 그대로 member로 매핑
		return "post req " + m +" id:  "+ m.getId()+" userName: " + m.getUserName()+" password: " + m.getPassword()
		+ " email: " + m.getEmail();
	 }
	@PutMapping("/http/put")
	public String putTest( ) {
	
		return "put req";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest( ) {
		return "delete req";
	}

}
