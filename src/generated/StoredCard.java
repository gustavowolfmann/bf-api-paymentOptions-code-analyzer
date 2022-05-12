package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class StoredCard {

    private Long id;

    private Integer securityStatus;

    private StoredCardStatus status;

    private Integer issuerId;

    private String issuerName;

    private Boolean issuerDefault;

    private String paymentMethodId;

    private String paymentMethodName;

    private PaymentMethodType paymentTypeCombo;

    private String type;

    private String paymentMethodIdCombo;

    private String paymentMethodNameCombo;

    private int timesUsedCombo;

    private PaymentMethodType paymentType;

    private CardHolder cardHolder;

    private Date creationDate;

    private int expirationMonth;

    private int expirationYear;

    private String firstSixDigits;

    private String lastFourDigits;

    private Date lastTimeUsed;

    private Date lastUpdated;

    private int timesUsed;

    private List<ValidationPrograms> validationProgramsCombo;

    private List<DescribablePaymentOptions> groupingConfigurations;

    private StoredCardDetail credit;

    private StoredExpression expression;

    private boolean defaultValue;
}
