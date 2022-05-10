package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentOptionsResponse {

    private List<Coupon> coupons;

    private PaymentOptions paymentOptions;

    private List<StoredCard> storedCards;

    private AccountMoneyInfo accountMoneyInfo;

    private List<WalletInfo> walletInfoList;

    private List<IdNameTuple> experiments;
}
