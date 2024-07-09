package com.project.ShoesProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;
}
