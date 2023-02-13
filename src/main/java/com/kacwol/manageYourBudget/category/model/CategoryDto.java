package com.kacwol.manageYourBudget.category.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
}
