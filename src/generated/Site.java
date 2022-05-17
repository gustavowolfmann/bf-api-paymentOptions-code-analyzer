package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Site {

    private String id;

    private String name;

    private Country country;

    private String defaultCurrencyId;

    private String immediatePayment;

    private String salesFeeMode;

    private List<Currency> currencies;

    private List<CurrencyConversion> currencyConversions;

    private List<Category> categories;

    private List<ShippingMethod> shippingMethods;

    private String secureExternal;

    private Map<String, Object> settings;
}
