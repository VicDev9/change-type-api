package com.desafio.api.tipocambio.infrastructure.feign.port;

import com.desafio.api.tipocambio.infrastructure.feign.response.ChangeTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;


@FeignClient(value = "ChangeTypeFeign", url = "urlChangeType")
public interface ChangeTypeFeign {

    @GetMapping(path = "", produces = "application/json")
    ChangeTypeResponse getChangeType(
            URI baseUrl);

}
