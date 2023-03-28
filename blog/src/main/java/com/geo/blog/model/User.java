package com.geo.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


enum Role {
	Admin, Manager, User
}
//ORM -> OBJECT --(mapping)-->QUERY
@Data //getter setter
@NoArgsConstructor // default constructor
@AllArgsConstructor // constructor with all arguments
@Builder
@Table(name= "USER")
@Entity // class -> table create
public class User {

//pk를 넘버로 auto_incrememt하고 싶으면 이래쓰고
//	@Id // pk
//	@Column(name="ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // Identitiy:프로젝트에서 연결된 DB의 넘버링 전략을 따라감(mysql이면 auto oracle이면 시퀀스 //auto: auto increment
//	private int id;


	@Id // pk
	@Column(nullable = false, length = 20,name="USER_ID")
	private String userId;

	@Column(nullable=false, length = 20, name="NICKNMAE")
	private  String userName;

	@Column(nullable = false, length = 100,name="PASSWORD")
	private String password;

	@Column(nullable = false, length = 50,name="EMAIL")
	private String email;

	@Column(name="ROLE")
	@Enumerated(EnumType.STRING)
	@ColumnDefault("'USER'") // 기본값 double quote안 single quote
	private Role role; // enum

	@Column(name="CREATEDAT")
	@CreationTimestamp
	private Timestamp createdAt;

}