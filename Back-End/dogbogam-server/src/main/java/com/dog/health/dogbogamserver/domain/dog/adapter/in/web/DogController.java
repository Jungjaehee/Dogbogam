package com.dog.health.dogbogamserver.domain.dog.adapter.in.web;

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
    public SuccessResponse<?> createDog(@RequestBody Dog dog) {
        createDogUseCase.createDog(dog);
        return SuccessResponse.created();
    }

    @PutMapping("/{id}")
    public SuccessResponse<?> updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        updateDogUseCase.updateDog(id, dog);
        return SuccessResponse.updated();
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<?> deleteDog(@PathVariable Long id) {
        deleteDogUseCase.deleteDog(id);
        return SuccessResponse.deleted();
    }

    @GetMapping("/{id}")
    public SuccessResponse<?> getDogDetails(@PathVariable Long id) {
        Optional<Dog> dog = findDogDetailsUseCase.findDogDetails(id);
        return dog.map(SuccessResponse::ok)
                .orElseGet(() -> SuccessResponse.ok(null));  // SuccessResponse.ok()로 처리
    }
}
