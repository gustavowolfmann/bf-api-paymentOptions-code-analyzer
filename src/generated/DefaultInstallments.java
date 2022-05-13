package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class DefaultInstallments {

    private List<Installment> installments;

    private String groupingType;
}
