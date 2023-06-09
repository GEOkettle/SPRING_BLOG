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
	@JoinColumn(name="USR_ID", nullable = false, foreignKey =@ForeignKey(ConstraintMode.NO_CONSTRAINT))// 니 FK쓸꺼? 아니잖아
	private User  userId;
	@EmbeddedId
	private UserBoardCompositeKey boardId;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	@Column(name = "CONTENT", nullable = false, length = 1000)
	private String content;

	@OneToMany(mappedBy = "boardId",fetch=FetchType.EAGER)
	private List<Reply> reply;

	@Column(name = "COUNT")
	@ColumnDefault("0")
	private int count;


	@Column(name = "CREATEDAT")
	@CreationTimestamp
	private Timestamp createdAt;

}
//@ManyToOne(fetch=FetchType.EAGER) defalut eager 게시글(many)에 있어서 글쓴이는 하나(one)밖에 없으니까 다 가져와 (EAGER)
//@OneToMany(fetch=FetchType.Lazy) default lazy  게시글(one)에 있어 댓글(many)는 여러개 있을 수 있으니까 필요할 때만 가져올게(LAZY)
//근데 우리는 글을 들어갈때 댓글이 다 보여야되니까(view) eager전략으로 가져올게




