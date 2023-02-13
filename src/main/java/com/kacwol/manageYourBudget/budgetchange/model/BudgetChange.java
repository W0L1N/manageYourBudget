package com.kacwol.manageYourBudget.budgetchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Inheritance
@NoArgsConstructor
@DiscriminatorColumn(name = "TYPE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    private Category category;

    private LocalDate dateTime;

    private BigDecimal value;

    private String description;

}
