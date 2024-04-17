package com.desafio.api.tipocambio.infrastructure.repository;

import com.desafio.api.tipocambio.domain.entity.ChangeTypeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionRepository extends JpaRepository<ChangeTypeLogEntity, Long> {

}
