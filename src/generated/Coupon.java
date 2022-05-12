package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Coupon {

    private long id;

    private BigDecimal amount;

    private Campaign campaign;
}
