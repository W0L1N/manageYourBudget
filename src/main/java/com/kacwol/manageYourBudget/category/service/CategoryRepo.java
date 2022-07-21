package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndUserId(Long id, Long userId);

    List<Category> findAllByUserId(Long userId);

    void deleteByIdAndUserId(Long id, Long userId);
}
