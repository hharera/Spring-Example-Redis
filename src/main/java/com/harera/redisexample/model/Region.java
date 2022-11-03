package com.harera.redisexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class Region extends BaseEntity {

    @Column(name = "arabicName")
    private String arabicName;

    @Column(name = "englishName")
    private String englishName;
}
