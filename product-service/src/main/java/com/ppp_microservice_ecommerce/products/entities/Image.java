package com.ppp_microservice_ecommerce.products.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Image {

    private String name;

    private String url;

}