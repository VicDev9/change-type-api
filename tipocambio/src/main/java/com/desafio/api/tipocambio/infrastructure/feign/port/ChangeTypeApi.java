package com.desafio.api.tipocambio.infrastructure.feign.port;

import com.desafio.api.tipocambio.infrastructure.feign.response.ChangeTypeResponse;

public interface ChangeTypeApi {

    ChangeTypeResponse getChangeType();

}
