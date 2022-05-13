package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class SecurityCodeLocation {

    private buyingflow.dto.payment.SecurityCodeLocation BACK;

    private buyingflow.dto.payment.SecurityCodeLocation FRONT;

    private buyingflow.dto.payment.SecurityCodeLocation MIN_VALUE;

    private buyingflow.dto.payment.SecurityCodeLocation MAX_VALUE;

    private buyingflow.dto.payment.SecurityCodeLocation[] $VALUES;
}
