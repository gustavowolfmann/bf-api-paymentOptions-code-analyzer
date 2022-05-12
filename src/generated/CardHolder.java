package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CardHolder {

    private String name;

    private BillingInfo document;
}
