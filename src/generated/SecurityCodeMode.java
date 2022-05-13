package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class SecurityCodeMode {

    private buyingflow.dto.payment.SecurityCodeMode MANDATORY;

    private buyingflow.dto.payment.SecurityCodeMode OPTIONAL;

    private buyingflow.dto.payment.SecurityCodeMode MIN_VALUE;

    private buyingflow.dto.payment.SecurityCodeMode MAX_VALUE;

    private buyingflow.dto.payment.SecurityCodeMode[] $VALUES;
}
