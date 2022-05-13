package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class ShippingMethod {

    private long id;

    private String name;

    private String status;

    private String siteId;

    private List<String> freeOptions;

    private List<String> shippingModes;
}
