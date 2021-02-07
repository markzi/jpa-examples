package com.jpa.examples.location.town;

import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;

@Getter
@Builder
public class TownResponse {
    private final String name;

    static Function<TownEntity, TownResponse> convert = (townEntity) ->
            TownResponse.builder()
                    .name(townEntity.getName())
                    .build();
}
