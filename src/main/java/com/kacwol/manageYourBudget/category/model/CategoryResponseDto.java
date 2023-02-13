package com.kacwol.manageYourBudget.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String name;

    private Long userId;

}
