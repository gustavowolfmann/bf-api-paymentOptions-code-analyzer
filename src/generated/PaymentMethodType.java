package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentMethodType {

    private buyingflow.dto.payment.PaymentMethodType CREDIT_CARD;

    private buyingflow.dto.payment.PaymentMethodType ATM;

    private buyingflow.dto.payment.PaymentMethodType TICKET;

    private buyingflow.dto.payment.PaymentMethodType ACCOUNT_MONEY;

    private buyingflow.dto.payment.PaymentMethodType DEBIT_CARD;

    private buyingflow.dto.payment.PaymentMethodType PREPAID_CARD;

    private buyingflow.dto.payment.PaymentMethodType BANK_TRANSFER;

    private buyingflow.dto.payment.PaymentMethodType DIGITAL_CURRENCY;

    private buyingflow.dto.payment.PaymentMethodType CASH;

    private buyingflow.dto.payment.PaymentMethodType CONSUMER_CREDITS;

    private buyingflow.dto.payment.PaymentMethodType DIGITAL_WALLET;

    private buyingflow.dto.payment.PaymentMethodType CRYPTO;

    private String type;

    private buyingflow.dto.payment.PaymentMethodType MIN_VALUE;

    private buyingflow.dto.payment.PaymentMethodType MAX_VALUE;

    private buyingflow.dto.payment.PaymentMethodType[] $VALUES;
}
