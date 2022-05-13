package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CreditOption {

    private List<Integer> installments;

    private BigDecimal minAllowedAmount;

    private BigDecimal maxAllowedAmount;

    private CreditOptionCondition condition;

    private Long differentialPricingId;

    private List<String> labels;
}
