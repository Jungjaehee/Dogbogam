package com.dog.health.dogbogamserver.domain.dog.application.service;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.UpdateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.CreateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.UpdateDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.DeleteDogUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.FindDogDetailsUseCase;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.CreateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.UpdateDogPort;
import com.dog.health.dogbogamserver.domain.dog.application.port.out.FindDogDetailsPort;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogService implements CreateDogUseCase, UpdateDogUseCase, DeleteDogUseCase, FindDogDetailsUseCase {

    private final CreateDogPort createDogPort;
    private final UpdateDogPort updateDogPort;
    private final FindDogDetailsPort findDogDetailsPort;

    @Override
    public void createDog(CreateDogDTO createDogDTO) {
        createDogPort.save(createDogDTO);
    }

    @Override
    public void updateDog(UpdateDogDTO updateDogDTO) {
        Optional<Dog> existingDog = findDogDetailsPort.findByDogId(updateDogDTO.getDogId());
        if (existingDog.isPresent()) {
            Dog updatedDog = Dog.builder()
                    .member(existingDog.get().getMember())
                    .breed(existingDog.get().getBreed())
                    .name(existingDog.get().getName())
                    .birthDate(existingDog.get().getBirthDate())
                    .dogId(existingDog.get().getDogId())
                    .isNeutered(existingDog.get().getIsNeutered())
                    .weight(existingDog.get().getWeight())
                    .gender(existingDog.get().getGender())
                    .build();
            updateDogPort.update(updatedDog);
        } else {
            throw new IllegalArgumentException("해당 아이디 {" + updateDogDTO.getDogId() + "}는 없습니다.");
        }
    }

    @Override
    public void deleteDog(Long dogId) {
        Optional<Dog> deleteDog = findDogDetailsPort.findByDogId(dogId);
        if (deleteDog.isPresent()) {
            deleteDog.get().deleteDog();
            updateDogPort.update(deleteDog.get());
        }
        // 이미 지워진 dog이거나 없는 dogId에 대한 예외처리 필요
    }

    @Override
    public Optional<Dog> findDogDetails(Long dogId) {
        return findDogDetailsPort.findByDogId(dogId);
    }
}
