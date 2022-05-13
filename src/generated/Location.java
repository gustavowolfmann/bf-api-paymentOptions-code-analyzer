package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Location {

    private BigDecimal longitude;

    private BigDecimal latitude;
}
