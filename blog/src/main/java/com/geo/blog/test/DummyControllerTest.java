package com.geo.blog.test;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geo.blog.model.User;
import com.geo.blog.repository.UserRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Error {

	public String errorMessage;
}

@RestController
public class DummyControllerTest {

	@Autowired // 의존성주입: spring이 컴포넌트 스캔할때 spring IOC가 보고
	// UserRepository라는 인터페이스 있으면 걔 인스턴스 하나띄울게
	private UserRepository userRepository;

	// 생성
	@PostMapping(value = "/test/signUp", produces = "text/html; charset=UTF-8")
//	public String signUp(String userId,String userName,String password,String email) { or
	public String signUp(User user) {

		System.out.println("userId:  " + user.getUserId());
		System.out.println("userName:  " + user.getUserName());
		System.out.println("password:  " + user.getPassword());
		System.out.println("email:  " + user.getEmail());
		System.out.println(user.getRole());
		if (user.getRole() == null)
			user.setRole(RoleType.user);

		userRepository.save(user);
		// 에러핸들링은 좀 있따가
		
		

		return "<h2>sign up successfully</h2>";
	}

	// 조회
	// {id}주소로 파라메터를 전달 받음
	@GetMapping("/test/user/{userId}")
	public User detail(@PathVariable String userId) {

		// findById 는 찾아봤을 때 있을 수도 있고 없을 수도 있고
		// 결과를 Optional에 싸서 줄테니 null값 판단하고 처리ㄱ
// pk int id 로할때
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//				@Override
//				public IllegalArgumentException get() {
//
//					return new IllegalArgumentException(id+"회원은 없습니다.");
//				}
//
//			});
		User user = userRepository.findByuserId(userId);
		// messageConverter가 지알아서 JSON으로 return
		return user;
	}

	// ALL 조회
	@GetMapping("/test/user/entireUsers")
	public List<User> entireUsers() {

		return userRepository.findAll();
	}

	// PAGE별 조회
	@GetMapping("/test/user/page") // 한페이지 갯수 정렬기준 정렬방식 get파라메터로 page=0 첫 페이지
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "userId", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<User> rawData = userRepository.findAll(pageable);

		List<User> users = rawData.getContent();

		return users;
	}

	// update
	@Transactional // return후 자동 commit
	@PutMapping("/test/user/{usrId}")
	public User updateUser(@PathVariable String usrId, @RequestBody User reqUsr) {

		System.out.println("parameter userId: " + usrId);
		System.out.println("req email" + reqUsr.getEmail());
		System.out.println("req pw" + reqUsr.getPassword());
		System.out.println("req userName" + reqUsr.getUserName());

		// jpa 영속성 컨텍스트 1차캐쉬의 객체를 따온다.
		User updUsr = userRepository.findByuserId(usrId);

		// 따온 객체의 속성을 변경하고 commit을 해버리면
		// 영속성 컨텍스트가 알아서 1차캐시의 영속화된 객체를 변경된값으로 업데이트를하고
		// DB에 박아준다.

		// 그럼 변경가능성이있다면
		// 변경되지도않을 값들 다 넣어주고 앞에서는 변경되지않을 갚들도 기존의 값으로 넣어서 통신쏘고? DynamicUpdate가 나은거 아니여
		// 그럼?
		updUsr.setPassword(reqUsr.getPassword());
		updUsr.setEmail(reqUsr.getEmail());
		updUsr.setUserName(reqUsr.getUserName());

		return updUsr;

	}

	@DeleteMapping("/test/user/delete/{usrId}")
	public List<Object> delete(@PathVariable String usrId) {

		User delUsr = userRepository.findByuserId(usrId);
		try {
			userRepository.delete(delUsr);

		} catch (Exception e) {
			System.out.println("exception occurred");
			System.out.println(e);

			List<Object> errorList = new ArrayList<>();
			Error error = new Error();
			error.setErrorMessage(e.getMessage());
			errorList.add(error);
			return errorList;
		}

		List<User> restList = userRepository.findAll();

		List<Object> restUsrs = new ArrayList<>();
		for (int i = 0; i < restList.size(); i++) {
			restUsrs.add(restList.get(i));
		}

		return restUsrs;
	}

	@DeleteMapping("/test/user/deleteAll")
	public String deleteAll() {
		userRepository.deleteAll();

		List<User> emptyUsrList = userRepository.findAll();

		System.out.println("emptyUsrList");
		System.out.println(emptyUsrList);
		if (emptyUsrList.isEmpty() == true)
			return "deleted All";
		else
			return "something went wrong";
	}
}