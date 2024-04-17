package com.desafio.api.tipocambio.controller;


import com.desafio.api.tipocambio.business.port.CurrencyConversionBusiness;
import com.desafio.api.tipocambio.domain.request.ConverterRequest;
import com.desafio.api.tipocambio.domain.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.desafio.api.tipocambio.util.Constants.MESSAGE_CONVERSION;
import static com.desafio.api.tipocambio.util.Constants.MESSAGE_LIST;

@RestController
@RequestMapping("/tipo-cambio")
public class ConversionController {

    @Autowired
    CurrencyConversionBusiness conversionMoneda;

    @PostMapping("/convert")
    public ResponseEntity<GenericResponse> convertCurrency(@RequestBody ConverterRequest convertidorRequest) throws IllegalAccessException {
        GenericResponse genericResponse = GenericResponse.builder()
                .message(MESSAGE_CONVERSION)
                .data(conversionMoneda.convertCurrency(convertidorRequest))
                .build();
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listResults() {
        GenericResponse genericResponse = GenericResponse.builder()
                .message(MESSAGE_LIST)
                .data(conversionMoneda.listRegistries())
                .build();
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
