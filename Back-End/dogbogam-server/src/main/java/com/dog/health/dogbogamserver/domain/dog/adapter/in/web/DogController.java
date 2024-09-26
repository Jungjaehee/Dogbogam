package com.dog.health.dogbogamserver.domain.dog.adapter.in.web;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.CreateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.UpdateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.DeleteDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.FindDogDetailsUseCase;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
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

    @PostMapping
    public SuccessResponse<?> createDog(@RequestBody CreateDogDTO createDogDTO) {
        createDogUseCase.createDog(createDogDTO);
        return SuccessResponse.created();
    }

    @PatchMapping
    public SuccessResponse<?> updateDog(@RequestBody Dog dog) {
        updateDogUseCase.updateDog(dog);
        return SuccessResponse.updated();
    }

    @DeleteMapping("/{dogId}")
    public SuccessResponse<?> deleteDog(@PathVariable Long dogId) {
        deleteDogUseCase.deleteDog(dogId);
        return SuccessResponse.deleted();
    }

    @GetMapping("/{dogId}")
    public SuccessResponse<?> getDogDetails(@PathVariable Long dogId) {
        Optional<Dog> dog = findDogDetailsUseCase.findDogDetails(dogId);
        return dog.map(SuccessResponse::ok)
                .orElseGet(() -> SuccessResponse.ok(null));  // SuccessResponse.ok()로 처리
    }
}
