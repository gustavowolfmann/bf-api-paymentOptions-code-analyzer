package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CardNumberValidation {

    private CardValidation validation;

    private int length;
}
