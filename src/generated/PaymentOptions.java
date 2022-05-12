package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentOptions {

    private List<PaymentItem> items;

    private List<PaymentMethod> paymentMethods;
}
