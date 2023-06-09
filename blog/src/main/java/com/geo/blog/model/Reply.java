package com.geo.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter setter
@NoArgsConstructor // default constructor
@AllArgsConstructor // constructor with all arguments
@Builder
@Table(name = "REPLY")
@Entity
public class Reply {

	@MapsId("boardId")
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),value={
        @JoinColumn(name = "USR_ID"),
        @JoinColumn(name = "BOARD_ID")
    })
	private Board boardId;
	@EmbeddedId
	private BoardReplyCompositeKey replyId;



	@Lob
	@Column(name = "CONTENT")
	private String content;

//	@ManyToOne
//	@JoinColumn(name = "BOARD_ID", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//	private Board boardId;
//
//	@ManyToOne
//	@JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//	private User userId;

	@CreationTimestamp
	@Column(name = "CREATEDAT")
	private Timestamp createdAt;
}
