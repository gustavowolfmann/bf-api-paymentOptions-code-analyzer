package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

public enum AccreditationTime {

    CLEARANCE_PAY_OFF_IMMEDIATE,
    CLEARANCE_PAY_OFF_LESS_AN_HOUR,
    CLEARANCE_PAY_OFF_1_DAY,
    CLEARANCE_PAY_OFF_1_2_DAY,
    CLEARANCE_PAY_OFF_1_3_DAY,
    CLEARANCE_INFORM_PAY_OFF_1_3_DAY,
    type
}
