package com.example.tss_project_withspring.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreationRequestDto {
    private String name;
    private String nationality;
    private LocalDate birthDate;
    private LocalDate deathDate;
}
