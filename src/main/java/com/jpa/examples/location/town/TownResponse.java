package com.jpa.examples.location.town;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.examples.location.address.AddressEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Builder
public class TownResponse {

    @JsonIgnore
    private final Long id;
    private final String name;

    private final List<String> firstLines;

    public static Function<TownEntity, TownResponse> convert = (townEntity) ->
            TownResponse.builder()
                    .id(townEntity.getId())
                    .name(townEntity.getName())
                    .firstLines(getFirstLines(townEntity))
                    .build();

    private static List<String> getFirstLines(TownEntity townEntity) {
        return townEntity.getAddress() != null ?
                townEntity.getAddress().stream().map(AddressEntity::getFirstLine).collect(Collectors.toList()) : Collections.emptyList();
    }

}
