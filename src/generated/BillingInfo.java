package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class BillingInfo {

    private String firstName;

    private String lastName;

    private String docType;

    private String docNumber;

    private String legalName;

    private String entityType;

    private Map<String> additionalInfo;

    private Boolean isDocumentPending;

    private Boolean isBillinginfoDefault;
}
