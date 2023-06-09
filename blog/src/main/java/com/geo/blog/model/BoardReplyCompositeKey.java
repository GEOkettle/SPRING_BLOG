package com.geo.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class BoardReplyCompositeKey implements Serializable{


	private UserBoardCompositeKey boardId;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="REPLY_ID")
	private int replyId;

}
