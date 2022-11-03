package com.harera.redisexample.model;

import lombok.Data;

@Data
public class RegionDto extends BaseEntityDto {

    private String arabicName;
    private String englishName;
}
