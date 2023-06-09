package com.geo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.geo.blog.model.User;


//자동으로 bean등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer>{

//	Optional<User> findByUsrId(String userId);
	public User findByuserId(String userId);

	public User findByUserIdAndPassword(String userId, String password);


	@Query(value="SELECT * FROM USER WHERE USR_ID=?1 AND PWD=?2",nativeQuery=true)
	User signIn(String userId,String password);
}
