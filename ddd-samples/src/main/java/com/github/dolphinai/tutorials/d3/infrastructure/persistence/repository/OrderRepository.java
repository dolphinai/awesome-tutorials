package com.github.dolphinai.tutorials.d3.infrastructure.persistence.repository;

import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

}
