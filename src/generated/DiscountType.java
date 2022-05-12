package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class DiscountType {

    private buyingflow.constants.DiscountType PERCENT;

    private buyingflow.constants.DiscountType FIXED;

    private String type;

    private buyingflow.constants.DiscountType MIN_VALUE;

    private buyingflow.constants.DiscountType MAX_VALUE;

    private buyingflow.constants.DiscountType[] $VALUES;
}
