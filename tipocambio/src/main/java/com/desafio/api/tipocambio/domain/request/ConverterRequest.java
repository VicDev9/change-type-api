package com.desafio.api.tipocambio.domain.request;

import lombok.Data;

@Data
public class ConverterRequest {

	public Double monto;
	public String monedaOrigen;
	public String monedaDestino;

}
