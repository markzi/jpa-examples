package com.jpa.examples.location.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.examples.location.town.TownResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Builder
public class AddressResponse {

    @JsonIgnore
    private Long id;
    private String firstLine;
    private String secondLine;
    private String thirdLine;
    private String postcode;
    private TownResponse town;

    static Function<AddressEntity, AddressResponse> convert = (addressEntity) ->
            AddressResponse.builder()
                    .id(addressEntity.getId())
                    .firstLine(addressEntity.getFirstLine())
                    .secondLine(addressEntity.getSecondLine())
                    .thirdLine(addressEntity.getThirdLine())
                    .postcode(addressEntity.getPostcode())
                    .town(TownResponse.convert.apply(addressEntity.getTown()))
                    .build();

    static Supplier<AddressResponse> nullable = () ->
            AddressResponse.builder().firstLine("null").build();
}
