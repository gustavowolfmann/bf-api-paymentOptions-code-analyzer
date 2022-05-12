package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class ActivationState {

    private String status;

    private buyingflow.dto.payment.ActivationState[] $VALUES;
}
