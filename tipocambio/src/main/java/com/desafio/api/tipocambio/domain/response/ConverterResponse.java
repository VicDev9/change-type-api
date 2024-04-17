package com.desafio.api.tipocambio.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConverterResponse {

	private Double monto;
	private Double montoConTipoDeCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private Double tipoCambio;
	
}
