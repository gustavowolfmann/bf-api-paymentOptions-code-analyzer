package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class AccreditationTime {

    private buyingflow.constants.AccreditationTime CLEARANCE_PAY_OFF_IMMEDIATE;

    private buyingflow.constants.AccreditationTime CLEARANCE_PAY_OFF_LESS_AN_HOUR;

    private buyingflow.constants.AccreditationTime CLEARANCE_PAY_OFF_1_DAY;

    private buyingflow.constants.AccreditationTime CLEARANCE_PAY_OFF_1_2_DAY;

    private buyingflow.constants.AccreditationTime CLEARANCE_PAY_OFF_1_3_DAY;

    private buyingflow.constants.AccreditationTime CLEARANCE_INFORM_PAY_OFF_1_3_DAY;

    private String type;

    private buyingflow.constants.AccreditationTime MIN_VALUE;

    private buyingflow.constants.AccreditationTime MAX_VALUE;

    private buyingflow.constants.AccreditationTime[] $VALUES;
}
