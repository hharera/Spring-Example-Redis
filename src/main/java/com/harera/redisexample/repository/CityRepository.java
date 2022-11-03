package com.harera.redisexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harera.redisexample.model.CityEntity;


@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

}
