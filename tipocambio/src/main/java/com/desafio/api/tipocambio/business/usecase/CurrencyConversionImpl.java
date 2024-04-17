package com.desafio.api.tipocambio.business.usecase;

import com.desafio.api.tipocambio.business.port.CurrencyConversionBusiness;
import com.desafio.api.tipocambio.domain.dto.ConverterDto;
import com.desafio.api.tipocambio.domain.entity.ChangeTypeLogEntity;
import com.desafio.api.tipocambio.domain.request.ConverterRequest;
import com.desafio.api.tipocambio.domain.response.ConverterResponse;
import com.desafio.api.tipocambio.infrastructure.feign.port.ChangeTypeApi;
import com.desafio.api.tipocambio.infrastructure.feign.response.ChangeTypeResponse;
import com.desafio.api.tipocambio.infrastructure.repository.CurrencyConversionRepository;
import com.desafio.api.tipocambio.util.Constants;
import com.desafio.api.tipocambio.util.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionImpl implements CurrencyConversionBusiness {

    @Autowired
    ChangeTypeApi changeTypeApi;

    @Autowired
    CurrencyConversionRepository conversionMonedaRepository;

    @Override
    public ConverterResponse convertCurrency(ConverterRequest convertidorRequest) throws IllegalAccessException {

        ChangeTypeResponse response = changeTypeApi.getChangeType();

        ConverterDto convertidorDto = ConverterDto.builder()
                .currencies(Utility.convertObjectToHashMap(response.getRates()))
                .build();

        Map<String, Double> results = calculateConversion(convertidorRequest, convertidorDto.getCurrencies());

        ConverterResponse convertidorResponse = ConverterResponse.builder()
                .monto(convertidorRequest.getMonto())
                .montoConTipoDeCambio(results.get(Constants.RESULT))
                .monedaDestino(convertidorRequest.getMonedaDestino().toUpperCase())
                .monedaOrigen(convertidorRequest.getMonedaOrigen().toUpperCase())
                .tipoCambio(results.get(Constants.CHANGE_TYPE))
                .build();
        ChangeTypeLogEntity changeTypeLog = new ModelMapper().map(convertidorResponse, ChangeTypeLogEntity.class);
        saveConversionRegistry(changeTypeLog);

        return convertidorResponse;
    }

    @Override
    public List<ConverterResponse> listRegistries() {
        List<ChangeTypeLogEntity> changeTypeLogList = conversionMonedaRepository.findAll();
        return changeTypeLogList.stream().map(changeTypeLog ->
                new ModelMapper().map(changeTypeLog, ConverterResponse.class)
        ).collect(Collectors.toList());
    }

    private Map<String, Double> calculateConversion(ConverterRequest convertidorRequest, Map<String, Number> currencies) {
        Map<String, Double> resultMap = new HashMap<>();
        Double changeTypeValue;
        if (convertidorRequest.getMonedaOrigen().equalsIgnoreCase(Constants.DOLAR_CURRENCY)) {
            changeTypeValue = currencies.get(convertidorRequest.getMonedaDestino().toUpperCase()).doubleValue();
            BigDecimal bd = new BigDecimal(changeTypeValue * convertidorRequest.getMonto()).setScale(2, RoundingMode.HALF_UP);
            resultMap.put(Constants.RESULT, bd.doubleValue());
            resultMap.put(Constants.CHANGE_TYPE, changeTypeValue);
            return resultMap;
        } else if (convertidorRequest.getMonedaDestino().equalsIgnoreCase(Constants.DOLAR_CURRENCY)) {
            changeTypeValue = currencies.get(convertidorRequest.getMonedaOrigen().toUpperCase()).doubleValue();
            BigDecimal bd = new BigDecimal(convertidorRequest.getMonto() / changeTypeValue).setScale(2, RoundingMode.HALF_UP);
            resultMap.put(Constants.RESULT, bd.doubleValue());
            resultMap.put(Constants.CHANGE_TYPE, changeTypeValue);
            return resultMap;
        }
        Double originValue = currencies.get(convertidorRequest.getMonedaOrigen().toUpperCase()).doubleValue();
        Double destinyValue = currencies.get(convertidorRequest.getMonedaDestino().toUpperCase()).doubleValue();
        changeTypeValue = new BigDecimal(destinyValue / originValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
        BigDecimal bd = new BigDecimal((convertidorRequest.getMonto() / originValue) * destinyValue).setScale(2, RoundingMode.HALF_UP);
        resultMap.put(Constants.RESULT, bd.doubleValue());
        resultMap.put(Constants.CHANGE_TYPE, changeTypeValue);
        return resultMap;
    }

    private void saveConversionRegistry(ChangeTypeLogEntity changeTypeLog) {
        conversionMonedaRepository.save(changeTypeLog);
    }
}
