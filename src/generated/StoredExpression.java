package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class StoredExpression {

    private String type;

    private String value;

    private List<StoredExpression> expressions;
}
