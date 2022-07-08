package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
