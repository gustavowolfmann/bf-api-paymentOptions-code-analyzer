package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

public enum DeferredCaptureType {

    SUPPORTED, UNSUPPORTED, DOES_NOT_APPLY, UNKNOWN, id
}
