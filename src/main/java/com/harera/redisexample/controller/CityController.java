package com.harera.redisexample.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.redisexample.model.City;
import com.harera.redisexample.model.CityRequest;
import com.harera.redisexample.model.CityResponse;
import com.harera.redisexample.service.CityService;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(summary = "List", description = "list all cities with zones ",
            tags = "City", responses = { @ApiResponse(responseCode = "200",
            description = "success|Ok") })
    public ResponseEntity<List<CityResponse>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "get city data", tags = "City",
            responses = {@ApiResponse(responseCode = "200",
                    description = "success|Ok")})
    public ResponseEntity<CityResponse> get(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.get(id));
    }
}
