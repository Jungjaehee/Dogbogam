package com.dog.health.dogbogamserver.domain.dog.adapter.in.web;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.UpdateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.*;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final CreateDogUseCase createDogUseCase;
    private final UpdateDogUseCase updateDogUseCase;
    private final DeleteDogUseCase deleteDogUseCase;
    private final FindDogDetailsUseCase findDogDetailsUseCase;
    private final FindDogsUseCase findDogsUseCase;

    @PostMapping
    public SuccessResponse<?> createDog(@Valid @RequestBody CreateDogDTO createDogDTO) {
        createDogUseCase.createDog(createDogDTO);
        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateDog(@Valid @RequestBody UpdateDogDTO updateDogDTO) {
        updateDogUseCase.updateDog(updateDogDTO);
        return SuccessResponse.updated();
    }

    @DeleteMapping("/{dogId}")
    public SuccessResponse<?> deleteDog(@PathVariable("dogId") Long dogId) {
        deleteDogUseCase.deleteDog(dogId);
        return SuccessResponse.deleted();
    }

    @GetMapping("/{dogId}")
    public SuccessResponse<?> getDogDetails(@PathVariable("dogId") Long dogId) {
        Optional<Dog> dog = findDogDetailsUseCase.findDogDetails(dogId);
        return dog.map(SuccessResponse::ok)
                .orElseGet(() -> SuccessResponse.ok(null));  // SuccessResponse.ok()로 처리
    }

    @GetMapping("/list/{memberId}")
    public SuccessResponse<?> getDogList(@PathVariable("memberId") Long memberId) {
        return SuccessResponse.ok(findDogsUseCase.findDogsByMemberId(memberId));
    }
}
