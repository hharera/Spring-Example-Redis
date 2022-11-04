package com.harera.redisexample.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.harera.redisexample.model.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.harera.redisexample.repository.CityRepository;
import com.harera.redisexample.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    private final RedisTemplate redisTemplate;

    private final ModelMapper mapper;

    public CityServiceImpl(CityRepository cityRepository, RedisTemplate redisTemplate, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.redisTemplate = redisTemplate;
        this.mapper = modelMapper;
    }

    @Override
    public City get(long id) {
        return cityRepository.findById(id).orElse(null);
    }
}
