package no.ntnu.idatt1005.foodi.controller;

import no.ntnu.idatt1005.foodi.model.DAO.RecipeDAO;
import no.ntnu.idatt1005.foodi.model.objects.Recipe;

import java.sql.SQLException;

/**
 * This class is responsible for handling the usage of
 * database operations regarding stored recipes in the frontend.
 *
 * @version 0.2.0
 * @author Snake727
 */
public class RecipeController {
  private RecipeDAO recipeDAO;

  public RecipeController() {
    recipeDAO = new RecipeDAO();
  }

  public void save(Recipe recipe) {
    try {
      recipeDAO.save(recipe);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(Recipe recipe) {
    recipeDAO.update(recipe);
  }

  public Recipe retrieve(Recipe recipe) {
      return recipeDAO.retrieve(recipe);
  }

  public void delete(Recipe recipe) {
      recipeDAO.delete(recipe);
  }
}