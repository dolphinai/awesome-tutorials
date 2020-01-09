package com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class CheckpointEntity implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String orderId;

	private String action;

	private String location;

	private String creator;

	@CreatedDate
	private Date creationDate;
}
