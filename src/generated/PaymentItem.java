package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentItem {

    private String itemId;

    private String differentialPricingId;

    private boolean immediatePayment;

    private List<String> groupingTypes;
}
