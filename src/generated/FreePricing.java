package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class FreePricing {

    private List<Installment> installments;

    private List<String> labels;

    private String groupingType;
}
