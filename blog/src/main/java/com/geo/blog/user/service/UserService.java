package com.geo.blog.user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geo.blog.dto.ResponseDto;
import com.geo.blog.model.User;
import com.geo.blog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public  ResponseDto<String> signUp(User user) {

		try {

			User isExist = userRepository.findByuserId(user.getUserId());
			if (isExist == null) {
			userRepository.save(user);
			return new ResponseDto<String>(HttpStatus.OK.value(),"OK") ;
			}else {
				System.out.println(isExist);
				 return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"ID duplicated") ;
			}
		} catch(Exception e) {

			e.printStackTrace();
			System.out.println("UserService: signUp() : " + e.getMessage());
			return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()) ;
		}


	}
		@Transactional(readOnly = true)// select할 시 트랜잭션 시작, 서비스 종료 시 종료(정합성유지)
		public ResponseDto<String> signIn(User user) {

//			User returnUsr = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword());
			User returnUsr = userRepository.signIn(user.getUserId(), user.getPassword());
			System.out.println(returnUsr);
			ResponseDto<String> result = new ResponseDto<String>();

			if(returnUsr != null) {
				result.setStatus(HttpStatus.OK.value());
				 result.setData("sign in successfully");
			}else {
				result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				result.setData("confirm your ID or password ");

			}

			return result;
		}


}
