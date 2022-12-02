package com.harera.redisexample.cache;

import java.util.List;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.harera.redisexample.model.City;

@Repository
public class CityCacheRepository extends RedisTemplate<String, Object> {

    private static final String CITIES_KEY = "cities";

    public CityCacheRepository(RedisConnectionFactory connectionFactory) {
        this.setConnectionFactory(connectionFactory);
    }

    public List<City> getCities() {
        return opsForHash().values(CITIES_KEY)
                .stream().map(City.class::cast).toList();
    }

    public void saveAll(List<City> cities) {
        cities.forEach(city -> opsForHash().put(CITIES_KEY, city.getId(), city));
    }

    public City getCity(Long id) {
        return (City) opsForHash().get(CITIES_KEY, String.valueOf(id));
    }

    public void saveCity(City city) {
        opsForHash().put(CITIES_KEY, city.getId(), city);
    }
}
