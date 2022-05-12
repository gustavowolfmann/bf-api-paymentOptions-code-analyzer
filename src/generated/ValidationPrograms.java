package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class ValidationPrograms {

    private String id;

    private Boolean mandatory;

    private Requirements requirements;
}
