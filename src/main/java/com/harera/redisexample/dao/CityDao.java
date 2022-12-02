package com.harera.redisexample.dao;


import javax.persistence.EntityNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.harera.redisexample.cache.CityCacheRepository;
import com.harera.redisexample.model.City;
import com.harera.redisexample.repository.CityRepository;

@Service
public class CityDao {

    private final CityRepository cityRepository;
    private final CityCacheRepository cityCacheRepository;

    public CityDao(CityRepository cityRepository, CityCacheRepository cityCacheRepository) {
        this.cityRepository = cityRepository;
        this.cityCacheRepository = cityCacheRepository;
    }

    public List<City> list() {
        try {
            List<City> cities = cityCacheRepository.getCities();
            if (cities.isEmpty()) {
                cities = cityRepository.findAll();
                cityCacheRepository.saveAll(cities);
            }
            return cities;
        } catch (Exception e) {
            e.printStackTrace();
            return cityRepository.findAll();
        }
    }

    public City get(Long id) {
        try {
            City city = cityCacheRepository.getCity(id);
            if (city == null) {
                city = cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                cityCacheRepository.saveCity(city);
            }
            return city;
        } catch (Exception e) {
            e.printStackTrace();
            return cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        }
    }
}
