package com.dog.health.dogbogamserver.domain.dog.application.service.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FindDogsResponseDto {
    List<FindDogsDogInfoDto> list;
}

