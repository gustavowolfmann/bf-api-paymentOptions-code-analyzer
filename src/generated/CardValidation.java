package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CardValidation {

    private buyingflow.dto.payment.CardValidation STANDARD;

    private buyingflow.dto.payment.CardValidation NONE;

    private buyingflow.dto.payment.CardValidation MIN_VALUE;

    private buyingflow.dto.payment.CardValidation MAX_VALUE;

    private buyingflow.dto.payment.CardValidation[] $VALUES;
}
