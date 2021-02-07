package com.jpa.examples.location.address;

import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;

@Getter
@Builder
public class AddressResponse {

    private String firstLine;
    private String secondLine;
    private String thirdLine;
    private String postcode;
    private Long townId;

    static Function<AddressEntity, AddressResponse> convert = (addressEntity) ->
            AddressResponse.builder()
                    .firstLine(addressEntity.getFirstLine())
                    .secondLine(addressEntity.getSecondLine())
                    .thirdLine(addressEntity.getThirdLine())
                    .postcode(addressEntity.getPostcode())
                    .townId(addressEntity.getTown().getId())
                    .build();
}
