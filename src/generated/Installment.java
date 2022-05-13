package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Installment {

    private int installment;

    private BigDecimal minAllowedAmount;

    private BigDecimal maxAllowedAmount;

    private BigDecimal installmentRate;

    private BigDecimal cftRate;

    private BigDecimal cfteaRate;

    private List<String> installmentRateCollector;

    private List<String> labels;

    private BigDecimal installmentAmount;

    private boolean defaultValue;
}
