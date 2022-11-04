package com.harera.redisexample.service;


import com.harera.redisexample.model.City;
import com.harera.redisexample.model.CityRequest;
import com.harera.redisexample.model.CityResponse;

public interface CityService {

    City get(long id);
}
