package com.jpa.examples.location.service;

import com.jpa.examples.location.address.AddressResponse;
import com.jpa.examples.location.town.TownResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.function.BiFunction;

@Getter
@Builder
public class LocationResponse {

    private String firstLine;
    private String secondLine;
    private String thirdLine;
    private String postcode;
    private String town;

    static BiFunction<AddressResponse, TownResponse, LocationResponse> convert = (addressResponse, townResponse) ->
            LocationResponse.builder().firstLine(addressResponse.getFirstLine())
                    .secondLine(addressResponse.getSecondLine())
                    .thirdLine(addressResponse.getThirdLine())
                    .postcode(addressResponse.getPostcode())
                    .town(townResponse.getName())
                    .build();
}
