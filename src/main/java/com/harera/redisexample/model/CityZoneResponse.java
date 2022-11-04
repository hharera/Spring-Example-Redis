package com.harera.redisexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CityZoneResponse extends RegionDto {

    @JsonProperty(value = "zones")
    private List<ZoneResponse> zoneList;
}
