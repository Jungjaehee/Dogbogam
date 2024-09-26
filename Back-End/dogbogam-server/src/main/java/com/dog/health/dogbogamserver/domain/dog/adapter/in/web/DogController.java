package com.dog.health.dogbogamserver.domain.dog.adapter.in.web;

import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogUseCase dogUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        return ResponseEntity.ok(dogUseCase.getDogById(id));
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs() {
        return ResponseEntity.ok(dogUseCase.getAllDogs());
    }

    @PostMapping
    public ResponseEntity<Void> registerDog(@RequestBody Dog dog) {
        dogUseCase.registerDog(dog);
        return ResponseEntity.ok().build();
    }
}
