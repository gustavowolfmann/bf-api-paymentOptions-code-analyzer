package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Currency {

    private String id;

    private String description;

    private String symbol;

    private int decimalPlaces;
}
