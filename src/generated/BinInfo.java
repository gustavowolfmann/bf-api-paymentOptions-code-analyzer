package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class BinInfo {

    private String pattern;

    private String installmentsPattern;

    private String exclusionPattern;
}
