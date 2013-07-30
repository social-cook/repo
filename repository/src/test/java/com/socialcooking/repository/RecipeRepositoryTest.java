package com.socialcooking.repository;

import com.socialcooking.domain.Recipe;
import com.socialcooking.domain.Section;
import com.socialcooking.domain.User;
import com.socialcooking.repository.api.RecipeRepository;
import com.socialcooking.repository.api.SectionRepository;
import com.socialcooking.repository.api.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/datasource-test.xml")
//May be H2 or MYSQL
@ActiveProfiles("H2")
@Transactional
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    public void findAllTest() {
        List<Recipe> recipes = recipeRepository.findAll();
        assertNotNull(recipes);
    }

    @Test
    public void findByIdTest() {
        Recipe expectedRecipe = new Recipe(1L, "фото1", "Блюдо1", "D:/1.jpeg", true, 4, 1);
        Recipe realRecipe = recipeRepository.findById(1L);
        assertEquals(expectedRecipe, realRecipe);
    }

    @Test
    public void saveTest() {
        Recipe expectedRecipe = new Recipe("фото4", "Блюдо4", "D:/4.jpeg", true, 4, 1);
        User user = userRepository.findById(1L);
        Section section = sectionRepository.findById(1L);

        expectedRecipe.setUser(user);
        expectedRecipe.setSection(section);
        Recipe realRecipe = recipeRepository.save(expectedRecipe);
        assertEquals(expectedRecipe, realRecipe);
    }

}
