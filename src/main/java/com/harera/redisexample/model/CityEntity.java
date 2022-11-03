package com.harera.redisexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "city")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class CityEntity extends City {
}
