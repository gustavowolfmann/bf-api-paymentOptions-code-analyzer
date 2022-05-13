package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class SecurityCodeInfo {

    private int length;

    private SecurityCodeLocation cardLocation;

    private SecurityCodeMode mode;
}
