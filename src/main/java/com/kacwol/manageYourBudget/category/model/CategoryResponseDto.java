package com.kacwol.manageYourBudget.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponseDto {

    private Long id;

    private String name;

    private Long userId;

}
