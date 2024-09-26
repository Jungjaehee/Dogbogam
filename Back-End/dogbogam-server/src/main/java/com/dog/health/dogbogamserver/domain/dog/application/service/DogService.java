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
        if (existingDog.isPresent() && !existingDog.get().getIsDeleted()) { // 삭제되지 않은 반려견 데이터만 업데이트 가능
            Dog updatedDog = existingDog.get();
            // 필요한 값 업데이트
            updatedDog.setName(updateDogDTO.getName());
            updatedDog.setBreed(updateDogDTO.getBreed());
            updatedDog.setGender(updateDogDTO.getGender());
            updatedDog.setBirthDate(updateDogDTO.getBirthDate());
            updatedDog.setImageName(updateDogDTO.getImageName());
            updatedDog.setImageUrl(updateDogDTO.getImageUrl());
            updatedDog.setIsNeutered(updateDogDTO.getIsNeutered());
            updatedDog.setWeight(updateDogDTO.getWeight());

            updateDogPort.update(updatedDog);
        } else {
            throw new IllegalArgumentException("해당 아이디 {" + updateDogDTO.getDogId() + "}는 없습니다.");
        }
    }

    @Override
    public void deleteDog(Long dogId) {
        Optional<Dog>  deleteDog = findDogDetailsPort.findByDogId(dogId);
        if (deleteDog.isPresent()) {
            // 삭제되지 않은 반려견 데이터만 업데이트 가능
            if (deleteDog.get().getIsDeleted()) {
                throw new IllegalArgumentException("이미지 삭제된 반려견 입니다.");
            }
            deleteDog.get().setIsDeleted(true);
            updateDogPort.update(deleteDog.get());
        }
    }

    @Override
    public Optional<Dog> findDogDetails(Long dogId) {
        return findDogDetailsPort.findByDogId(dogId);
    }
}
