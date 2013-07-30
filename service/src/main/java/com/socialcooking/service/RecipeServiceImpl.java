package com.socialcooking.service;


import com.socialcooking.domain.Recipe;
import com.socialcooking.repository.api.GenericRepository;
import com.socialcooking.repository.api.RecipeRepository;
import com.socialcooking.service.api.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeServiceImpl extends GenericServiceImpl<Recipe> implements RecipeService {

    @Autowired
    private RecipeRepository commentRepository;

    @Override
    protected GenericRepository<Recipe> getRepository() {
        return commentRepository;
    }

}
