package com.harera.redisexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "city")
@Entity
@Setter
@Getter
@NoArgsConstructor
@RedisHash("Student")
public class City extends Region {

}
