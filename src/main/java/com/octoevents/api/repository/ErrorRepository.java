package com.octoevents.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.octoevents.api.model.Error;

@Repository
public interface ErrorRepository extends CrudRepository<Error, Integer>{
}
