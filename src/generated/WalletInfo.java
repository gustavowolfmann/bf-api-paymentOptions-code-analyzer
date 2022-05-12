package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class WalletInfo {

    private String currencyId;

    private BigDecimal available;

    private QuoteInfo quote;

    private boolean hasSecondPassword;

    private String authCode;

    private boolean authCodeRequired;

    private boolean skipPassword;

    private AccountMoneyAssetStatus assetStatus;
}
