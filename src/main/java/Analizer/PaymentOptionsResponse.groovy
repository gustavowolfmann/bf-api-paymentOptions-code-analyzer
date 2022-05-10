package buyingflow.dto.response

import buyingflow.dto.IdNameTuple
import buyingflow.dto.payment.AccountMoneyInfo
import buyingflow.dto.payment.Coupon
import buyingflow.dto.payment.PaymentOptions
import buyingflow.dto.payment.StoredCard
import buyingflow.dto.payment.WalletInfo
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

public class PaymentOptionsResponse implements GenericResponse {
    Collection<Coupon> coupons
    PaymentOptions paymentOptions
    Collection<StoredCard> storedCards
    AccountMoneyInfo accountMoneyInfo
    Collection<WalletInfo> walletInfoList
    List<IdNameTuple> experiments

    public PaymentOptionsResponseBuilder asBuilder() {
        new PaymentOptionsResponseBuilder()
            .coupons(coupons ? coupons.collect {it.asBuilder().build()} : [])
            .storedCards(storedCards ? storedCards.collect {it.asBuilder().build()} : [])
            .paymentOptions(paymentOptions ? paymentOptions.asBuilder().build() : null)
            .accountMoneyInfo(accountMoneyInfo ? accountMoneyInfo.asBuilder().build() : null)
            .walletInfoList(walletInfoList ? walletInfoList.collect {it.asBuilder().build()} : [])
            .experiments(experiments ? experiments.collect {it.asBuilder().build()} : [])
    }
}

@Builder(builderStrategy = ExternalStrategy, forClass = PaymentOptionsResponse)
class PaymentOptionsResponseBuilder {}
