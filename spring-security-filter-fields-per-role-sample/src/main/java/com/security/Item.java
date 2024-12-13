package com.security;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@AuthorityToJsonViewMappings({
        @AuthorityToJsonViewMapping(authority = "USER",view = WithoutOwnerView.class),
        @AuthorityToJsonViewMapping(authority = "ADMIN",view = WithOwnerView.class)
})
@Getter
@Setter
public class Item {

    @JsonView(WithoutOwnerView.class)
    private String id;
    @JsonView(WithoutOwnerView.class)
    private String name;
    @JsonView(WithOwnerView.class)
    private String owner;
}

interface WithoutOwnerView {}
interface WithOwnerView extends WithoutOwnerView {}