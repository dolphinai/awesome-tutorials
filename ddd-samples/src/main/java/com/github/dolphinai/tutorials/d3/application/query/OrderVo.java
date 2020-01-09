package com.github.dolphinai.tutorials.d3.application.query;

import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.OrderEntity;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Order Basic Information")
public interface OrderVo extends Serializable {

	String getId();

	String getName();

}
