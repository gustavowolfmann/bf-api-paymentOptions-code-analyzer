package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class CreditLineDetail {

    private BigDecimal availableBalance;

    private List<CreditOption> creditOptions;

    private CustomDate dueDate;
}
