package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Campaign {

    private long id;

    private String name;

    private String code;

    private DiscountType discountType;

    private BigDecimal value;

    private BigDecimal minPaymentAmount;

    private BigDecimal maxCouponAmount;

    private BigDecimal totalAmountLimit;
}
