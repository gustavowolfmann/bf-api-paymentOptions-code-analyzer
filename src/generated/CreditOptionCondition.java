package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CreditOptionCondition {

    private BigDecimal td;

    private String tea;

    private String tna;

    private BigDecimal iva;

    private String cft;

    private String cftea;

    private BigDecimal cat;

    private String additionalIof;

    private String iof;

    private String iofRate;

    private String ceta;

    private String cetm;

    private String tem;

    private Map<String> rawCreditOptionCondition;
}
