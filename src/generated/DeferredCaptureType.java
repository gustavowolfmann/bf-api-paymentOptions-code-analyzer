package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class DeferredCaptureType {

    private buyingflow.dto.payment.DeferredCaptureType SUPPORTED;

    private buyingflow.dto.payment.DeferredCaptureType UNSUPPORTED;

    private buyingflow.dto.payment.DeferredCaptureType DOES_NOT_APPLY;

    private buyingflow.dto.payment.DeferredCaptureType UNKNOWN;

    private String id;

    private buyingflow.dto.payment.DeferredCaptureType MIN_VALUE;

    private buyingflow.dto.payment.DeferredCaptureType MAX_VALUE;

    private buyingflow.dto.payment.DeferredCaptureType[] $VALUES;
}
