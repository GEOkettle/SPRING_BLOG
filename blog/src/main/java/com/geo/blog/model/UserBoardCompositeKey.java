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
public class UserBoardCompositeKey implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 *
	 */

	private String userId;


	@Column(name="BOARD_ID")
	private int boardId;


}
