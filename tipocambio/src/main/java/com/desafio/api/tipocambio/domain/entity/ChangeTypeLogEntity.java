package com.desafio.api.tipocambio.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CHANGE_TYPE_LOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTypeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "monto_con_tipo_de_cambio", nullable = false)
    private Double montoConTipoDeCambio;

    @Column(name = "moneda_origen", nullable = false)
    private String monedaOrigen;

    @Column(name = "moneda_destino", nullable = false)
    private String monedaDestino;

    @Column(name = "tipo_cambio", nullable = false)
    private Double tipoCambio;

}
