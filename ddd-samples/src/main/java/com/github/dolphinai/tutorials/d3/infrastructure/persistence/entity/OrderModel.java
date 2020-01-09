package com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public interface OrderModel extends Serializable {

	@NotBlank
	String getId();

	@NotBlank
	String getName();

}
