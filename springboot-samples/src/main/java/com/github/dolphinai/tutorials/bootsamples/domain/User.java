package com.github.dolphinai.tutorials.bootsamples.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("User value object")
@Data
public class User implements Serializable {

	private String id;
	private String name;

	public static User of(String id) {
		User user = new User();
		user.setId(id);
		return user;
	}
}
