package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class QuoteInfo {

    private BigDecimal exchangeRate;

    private String token;
}
