package com.geo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geo.blog.model.User;


//자동으로 bean등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer>{

//	Optional<User> findByUsrId(String userId);
	public User findByuserId(String userId);



}
