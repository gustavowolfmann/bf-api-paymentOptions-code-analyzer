package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class StoredAccount {

    private String status;

    private String statusDetail;
}
