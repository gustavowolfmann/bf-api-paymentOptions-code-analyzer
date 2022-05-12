package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class StoredCardStatus {

    private buyingflow.dto.payment.StoredCardStatus ACTIVE;

    private buyingflow.dto.payment.StoredCardStatus INACTIVE;

    private buyingflow.dto.payment.StoredCardStatus PENDING;

    private buyingflow.dto.payment.StoredCardStatus EXPIRED;

    private String status;

    private buyingflow.dto.payment.StoredCardStatus MIN_VALUE;

    private buyingflow.dto.payment.StoredCardStatus MAX_VALUE;

    private buyingflow.dto.payment.StoredCardStatus[] $VALUES;
}
