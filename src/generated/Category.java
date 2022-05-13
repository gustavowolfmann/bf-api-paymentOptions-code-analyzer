package com.mercadolibre.payments.domain.payment_options.wrapper;

import lombok.Data;

@Data()
public class Category {

    private String id;

    private String name;

    private String picture;

    private String permaLink;

    private long totalItemsInThisCategory;

    private String attributeTypes;

    private Map<String> settings;

    private long metaCategId;

    private boolean attributable;

    private List<IdNameTuple> pathFromRoot;
}
