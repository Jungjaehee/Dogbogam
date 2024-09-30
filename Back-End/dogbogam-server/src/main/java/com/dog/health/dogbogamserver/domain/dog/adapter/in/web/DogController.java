package com.dog.health.dogbogamserver.domain.dog.adapter.in.web;

import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.CreateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.adapter.in.web.dto.UpdateDogDTO;
import com.dog.health.dogbogamserver.domain.dog.application.port.in.*;
import com.dog.health.dogbogamserver.domain.dog.domain.Dog;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.web.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Dog API", description = "반려견 정보 관리 API")
@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final CreateDogUseCase createDogUseCase;
    private final UpdateDogUseCase updateDogUseCase;
    private final DeleteDogUseCase deleteDogUseCase;
    private final FindDogDetailsUseCase findDogDetailsUseCase;
    private final FindDogsUseCase findDogsUseCase;
    private final MemberService memberService;

    @Operation(summary = "반려견 등록", description = "새로운 반려견 정보를 등록합니다.")
    @PostMapping
    public SuccessResponse<?> createDog(
            @Parameter(description = "로그인된 사용자의 정보", required = true) @AuthenticationPrincipal MemberPrincipal memberPrincipal,
            @Valid @RequestBody CreateDogDTO createDogDTO) {
        createDogUseCase.createDog(createDogDTO, memberPrincipal.getMemberId());
        return SuccessResponse.created();
    }

    @Operation(summary = "반려견 정보 수정", description = "반려견의 정보를 수정합니다.")
    @PatchMapping
    public SuccessResponse<?> updateDog(
            @Parameter(description = "로그인된 사용자의 정보", required = true) @AuthenticationPrincipal MemberPrincipal memberPrincipal,
            @Valid @RequestBody UpdateDogDTO updateDogDTO) {
        updateDogUseCase.updateDog(updateDogDTO, memberPrincipal.getMemberId());
        return SuccessResponse.updated();
    }

    @Operation(summary = "반려견 삭제", description = "반려견 정보를 삭제합니다.")
    @DeleteMapping("/{dogId}")
    public SuccessResponse<?> deleteDog(
            @Parameter(description = "삭제할 반려견의 ID", required = true) @PathVariable("dogId") Long dogId) {
        deleteDogUseCase.deleteDog(dogId);
        return SuccessResponse.deleted();
    }

    @Operation(summary = "반려견 상세 정보 조회", description = "반려견의 상세 정보를 조회합니다.")
    @GetMapping("/{dogId}")
    public SuccessResponse<?> getDogDetails(
            @Parameter(description = "조회할 반려견의 ID", required = true) @PathVariable("dogId") Long dogId) {
        Optional<Dog> dog = findDogDetailsUseCase.findDogDetails(dogId);
        return SuccessResponse.created(dog.map(SuccessResponse::ok)
                .orElseGet(() -> SuccessResponse.ok(null)));
    }

    @Operation(summary = "반려견 목록 조회", description = "사용자의 반려견 목록을 조회합니다.")
    @GetMapping("/list")
    public SuccessResponse<?> getDogList(
            @Parameter(description = "로그인된 사용자의 정보", required = true) @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return SuccessResponse.ok(findDogsUseCase.findDogsByMemberId(memberPrincipal.getMemberId()));
    }
}
