package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Issuer {

    private int id;

    private String name;

    private boolean defaultIssuer;

    private IssuerDiscount issuerDiscount;
}
