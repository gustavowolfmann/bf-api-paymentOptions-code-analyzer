package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentMethodDigitalCurrency {

    private String id;

    private String name;

    private String thumbnail;

    private List<String> additionalInfo;

    private int minAccreditationDays;

    private int maxAccreditationDays;

    private int priority;

    private Issuer issuer;

    private PaymentMethodType type;

    private DeferredCaptureType deferredCapture;

    private List<DescribablePaymentOptions> groupingConfigurations;

    private ActivationState status;

    private String statusDetails;

    private AccreditationTime accreditationTime;

    private boolean defaultValue;
}
