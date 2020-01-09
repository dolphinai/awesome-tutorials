package com.github.dolphinai.tutorials.d3.application.query;

import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.OrderEntity;
import io.swagger.annotations.ApiModel;

@ApiModel("Order Information")
public interface OrderDetailVo extends OrderVo {

	static OrderDetailVo of(OrderEntity entity) {
		return new OrderDetailVo() {
			@Override
			public String getId() {
				return entity.getId();
			}

			@Override
			public String getName() {
				return entity.getId();
			}
		};
	}
}
