package com.desafio.api.tipocambio.infrastructure.feign.usecase;

import com.desafio.api.tipocambio.infrastructure.feign.response.ChangeTypeResponse;
import com.desafio.api.tipocambio.infrastructure.feign.port.ChangeTypeApi;
import com.desafio.api.tipocambio.infrastructure.feign.port.ChangeTypeFeign;
import com.desafio.api.tipocambio.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;

@Repository
public class ChangeTypeApiImpl implements ChangeTypeApi {

    @Autowired
    private ChangeTypeFeign changeTypeFeign;

    @Value(Constants.URL_FEIGN)
    private String urlApi;

    @Override
    public ChangeTypeResponse getChangeType() {
        return changeTypeFeign.getChangeType(URI.create(urlApi));
    }
}
