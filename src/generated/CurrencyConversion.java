package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CurrencyConversion {

    private String from;

    private String to;

    private BigDecimal ratio;
}
