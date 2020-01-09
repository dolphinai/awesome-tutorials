package com.github.dolphinai.tutorials.d3.infrastructure.persistence.repository;

import com.github.dolphinai.tutorials.d3.infrastructure.persistence.entity.CheckpointEntity;
import org.springframework.data.repository.CrudRepository;

public interface CheckpointRepository extends CrudRepository<CheckpointEntity, Long> {

}
