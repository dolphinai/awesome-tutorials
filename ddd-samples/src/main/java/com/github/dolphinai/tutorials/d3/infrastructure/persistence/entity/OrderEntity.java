package com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public final class OrderEntity implements OrderModel {

	@Id
	private String id;

	private String name;

	private String destination;

	private String owner;

	private String status;

	@CreatedDate
	private Date creationDate;

	private Date completedDate;

}
