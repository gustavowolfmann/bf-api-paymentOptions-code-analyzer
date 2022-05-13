package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Country {

    private String id;

    private String name;

    private String locale;

    private String currencyId;

    private String decimalSeparator;

    private String thousandsSeparator;

    private String timeZone;

    private Location geoInformation;

    private List<IdNameTuple> states;
}
