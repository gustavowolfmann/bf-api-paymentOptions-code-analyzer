package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class AccountMoneyAssetStatus {

    private buyingflow.dto.AccountMoneyAssetStatus INVESTED;

    private buyingflow.dto.AccountMoneyAssetStatus AVAILABLE;

    private String type;

    private buyingflow.dto.AccountMoneyAssetStatus MIN_VALUE;

    private buyingflow.dto.AccountMoneyAssetStatus MAX_VALUE;

    private buyingflow.dto.AccountMoneyAssetStatus[] $VALUES;
}
