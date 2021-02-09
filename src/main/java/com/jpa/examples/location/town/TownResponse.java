package com.jpa.examples.location.town;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

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
                    .firstLines(townEntity.getAddress().stream().map(addressEntity -> addressEntity.getFirstLine()).collect(Collectors.toList()))
                    .build();
}
