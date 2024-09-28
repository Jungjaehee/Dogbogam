package com.dog.health.dogbogamserver.domain.healthProblems.adapter.out.persistence;

import com.dog.health.dogbogamserver.domain.dog.adapter.out.persistence.DogEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "health_problem")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthProblemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_problem_id", nullable = false)
    private Long healthProblemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id", nullable = false)
    private DogEntity dog;

    @Column(name = "problem", nullable = false)
    private String problem;
}
