package com.harera.redisexample.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.harera.redisexample.model.City;
import com.harera.redisexample.model.CityEntity;
import com.harera.redisexample.model.CityRequest;
import com.harera.redisexample.model.CityResponse;
import com.harera.redisexample.repository.CityRepository;
import com.harera.redisexample.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityDao;

    private final RedisTemplate redisTemplate;

    private final ModelMapper mapper;

    public CityServiceImpl(CityRepository cityDao, RedisTemplate redisTemplate, ModelMapper modelMapper) {
        this.cityDao = cityDao;
        this.redisTemplate = redisTemplate;
        this.mapper = modelMapper;
    }


    @Override
    public City get(long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        return null;

//        Optional<CityEntity> city = cityDao.findById(id);
//        if (city.isEmpty())
//            return null;
//
//        operations.set(key, city.get(), 10, TimeUnit.SECONDS);
//        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
//
//        return city.get();
    }

    @Override
    public CityResponse create(CityRequest cityRequest) {
        City city = mapper.map(cityRequest, City.class);
        CityEntity cityEntity = mapper.map(city, CityEntity.class);
        cityDao.save(cityEntity);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("city_" + cityEntity.getId(), cityEntity, 10, TimeUnit.SECONDS);
        return mapper.map(city, CityResponse.class);
    }

//
//    @Override
//    public Long saveCity(City city) {
//        return cityDao.save(city);
//    }
//
//    /**
//     * 更新城市逻辑：
//     * 如果缓存存在，删除
//     * 如果缓存不存在，不操作
//     */
//    @Override
//    public Long updateCity(City city) {
//        Long ret = cityDao.updateCity(city);
//
//        // 缓存存在，删除缓存
//        String key = "city_" + city.getId();
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            redisTemplate.delete(key);
//
//            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
//        }
//
//        return ret;
//    }
//
//    @Override
//    public Long deleteCity(Long id) {
//
//        Long ret = cityDao.deleteCity(id);
//
//        // 缓存存在，删除缓存
//        String key = "city_" + id;
//        boolean hasKey = redisTemplate.hasKey(key);
//        if (hasKey) {
//            redisTemplate.delete(key);
//
//            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
//        }
//        return ret;
//    }

}
