package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentMethod {

    private String Id;

    private String Name;

    private String Thumbnail;

    private List<String> AdditionalInfo;

    private int MinAccreditationDays;

    private int MaxAccreditationDays;

    private int Priority;

    private PaymentMethodType Type;

    private Issuer Issuer;

    private DeferredCaptureType DeferredCapture;

    private List<DescribablePaymentOptions> GroupingConfigurations;

    private ActivationState Status;

    private String StatusDetails;

    private BigDecimal MinAllowedAmount;

    private BigDecimal MaxAllowedAmount;

    private AccreditationTime AccreditationTime;
}
