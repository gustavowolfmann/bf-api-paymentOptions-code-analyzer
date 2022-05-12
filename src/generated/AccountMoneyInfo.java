package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class AccountMoneyInfo {

    private BigDecimal availableAccountMoney;

    private boolean hasSecondPassword;

    private String authCode;

    private boolean authCodeRequired;

    private boolean skipPassword;

    private AccountMoneyAssetStatus assetStatus;
}
