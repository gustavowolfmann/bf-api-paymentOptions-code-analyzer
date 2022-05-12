package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Requirements {

    private String cardTokenized;

    private String amountLowerThan;
}
