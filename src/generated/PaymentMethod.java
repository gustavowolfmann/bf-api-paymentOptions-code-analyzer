package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class PaymentMethod {

    private String getId;

    private String getName;

    private String getThumbnail;

    private List<String> getAdditionalInfo;

    private int getMinAccreditationDays;

    private int getMaxAccreditationDays;

    private int getPriority;

    private PaymentMethodType getType;

    private Issuer getIssuer;

    private DeferredCaptureType getDeferredCapture;

    private List<DescribablePaymentOptions> getGroupingConfigurations;

    private ActivationState getStatus;

    private String getStatusDetails;

    private BigDecimal getMinAllowedAmount;

    private BigDecimal getMaxAllowedAmount;

    private AccreditationTime getAccreditationTime;
}
