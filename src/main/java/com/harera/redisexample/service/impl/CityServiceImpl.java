package com.harera.redisexample.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.harera.redisexample.dao.CityDao;
import com.harera.redisexample.model.City;
import com.harera.redisexample.model.CityResponse;
import com.harera.redisexample.service.CityService;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;
    private final ModelMapper modelMapper;


    public CityServiceImpl(CityDao cityDao, ModelMapper modelMapper) {
        this.cityDao = cityDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public CityResponse get(long id) {
        return modelMapper.map(cityDao.get(id), CityResponse.class);
    }

    @Override
    public List<CityResponse> list() {
        List<City> list = cityDao.list();
        return list.stream().map(city -> modelMapper.map(city, CityResponse.class)).toList();
    }
}
