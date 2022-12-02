package com.harera.redisexample.service;


import java.util.List;

import com.harera.redisexample.model.CityResponse;

public interface CityService {

    CityResponse get(long id);
    List<CityResponse> list();
}
