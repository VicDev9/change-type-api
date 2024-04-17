package com.desafio.api.tipocambio.business.port;

import com.desafio.api.tipocambio.domain.request.ConverterRequest;
import com.desafio.api.tipocambio.domain.response.ConverterResponse;

import java.util.List;


public interface CurrencyConversionBusiness {

	public ConverterResponse convertCurrency(ConverterRequest convertidorRequest) throws IllegalAccessException;

	public List<ConverterResponse> listRegistries();
	
}
