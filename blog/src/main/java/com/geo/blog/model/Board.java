package com.geo.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter setter
@NoArgsConstructor // default constructor
@AllArgsConstructor // constructor with all arguments
@Builder
//@Table(name = "BOARD",
//indexes= {
//		@Index(name="usrBrdIndex", columnList ="USER_ID,BOARD_ID")
//})//인덱스 만들기
@Table(name="BOARD")
@Entity
public class Board {



	@MapsId("userId")
	@ManyToOne(fetch=FetchType.EAGER) //관계 설정: 다대일  MANY(BOARD[현재obj]) ONE(USER[mapping하려는obj])
	@JoinColumn(name="USER_ID", nullable = false, foreignKey =@ForeignKey(ConstraintMode.NO_CONSTRAINT))// 니 FK쓸꺼? 아니잖아
	private User  userId;
	@EmbeddedId
	private UserBoardCompositeKey boardId;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	@Column(name = "CONTENT", nullable = false, length = 1000)
	private String content;

//	@OneToMany(mappedBy = "boardId",fetch=FetchType.LAZY)
//	private List<Reply> reply;

	@Column(name = "COUNT")
	@ColumnDefault("0")
	private int count;


	@Column(name = "CREATEDAT")
	@CreationTimestamp
	private Timestamp createdAt;

}




