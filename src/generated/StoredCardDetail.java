package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class StoredCardDetail {

    private StoredAccount account;

    private BigDecimal availableLimit;

    private BigDecimal totalLimit;

    private String cardStatus;
}
