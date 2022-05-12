package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class IssuerDiscount {

    private BigDecimal discountValue;

    private BigDecimal maxDiscount;

    private BigDecimal paymentMinAmount;
}
