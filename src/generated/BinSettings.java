package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class BinSettings {

    private CardNumberValidation cardNumber;

    private BinInfo bin;

    private SecurityCodeInfo securityCode;
}
