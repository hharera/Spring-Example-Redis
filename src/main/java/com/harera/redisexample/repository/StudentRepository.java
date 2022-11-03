package com.harera.redisexample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harera.redisexample.model.City;

@Repository
public interface StudentRepository extends CrudRepository<City, String> {

}