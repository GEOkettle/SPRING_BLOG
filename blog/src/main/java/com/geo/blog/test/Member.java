package com.geo.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter
//@Getter
//@Setter
//@AllArgsConstructor //모든 생성자를 파라메터로 받는 생성자
@NoArgsConstructor  // default construct
//@RequiredArgsConstructor //final 붙은 멤버변수 constructor
public class Member {
	
	
	private int id;
	private  String userName;
	private String password;
	private String email;


	
	@Builder
	public Member(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	
}