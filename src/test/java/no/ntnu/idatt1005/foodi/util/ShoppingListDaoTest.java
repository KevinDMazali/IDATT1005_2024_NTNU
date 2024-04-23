//package no.ntnu.idatt1005.foodi.util;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;
//import no.ntnu.idatt1005.foodi.model.DAO.IngredientDAO;
//import no.ntnu.idatt1005.foodi.model.DAO.RecipeDAO;
//import no.ntnu.idatt1005.foodi.model.DAO.ShoppingListDAO;
//import no.ntnu.idatt1005.foodi.model.DAO.UserDAO;
//import no.ntnu.idatt1005.foodi.model.objects.dtos.Ingredient;
//import no.ntnu.idatt1005.foodi.model.objects.dtos.User;
//import no.ntnu.idatt1005.foodi.model.repository.Database;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class ShoppingListDaoTest {
//
//  private static ShoppingListDAO shoppingListDAO;
//  private static UserDAO userDAO;
//  private static IngredientDAO ingredientDAO;
//  private static RecipeDAO recipeDAO;
//  private static User testUser;
//  private static Ingredient testIngredient1;
//  private static Ingredient testIngredient2;
//  private static Ingredient testIngredient3;
//  private static Ingredient testIngredient4;
//  private static Ingredient testIngredient5;
//
//  @BeforeEach
//  public void setUp() throws SQLException {
//    // Initialize the main database
//    Database.initializeEmpty();
//
//    // Initialize new DAO objects
//    ingredientDAO = new IngredientDAO();
//    userDAO = new UserDAO();
//    shoppingListDAO = new ShoppingListDAO();
//    recipeDAO = new RecipeDAO();
//
//    // Create a test user
//    testUser = new User(1, "Test User");
//    userDAO.saveUser(testUser.name());
//
//    // Create new test ingredients
//    testIngredient1 = new Ingredient(1, "Test Ingredient 1", Ingredient.Unit.GRAM,
//        Ingredient.Category.VEGETABLE);
//    testIngredient2 = new Ingredient(2, "Test Ingredient 2", Ingredient.Unit.GRAM,
//        Ingredient.Category.VEGETABLE);
//    testIngredient3 = new Ingredient(3, "Test Ingredient 3", Ingredient.Unit.GRAM,
//        Ingredient.Category.VEGETABLE);
//    testIngredient4 = new Ingredient(4, "Test Ingredient 4", Ingredient.Unit.GRAM,
//        Ingredient.Category.VEGETABLE);
//    testIngredient5 = new Ingredient(5, "Test Ingredient 5", Ingredient.Unit.GRAM,
//        Ingredient.Category.VEGETABLE);
//
//    // Save the test ingredients to the database
//    ingredientDAO.saveIngredientObject(testIngredient1);
//    ingredientDAO.saveIngredientObject(testIngredient2);
//    ingredientDAO.saveIngredientObject(testIngredient3);
//    ingredientDAO.saveIngredientObject(testIngredient4);
//    ingredientDAO.saveIngredientObject(testIngredient5);
//
//    // Create a new test recipe
//    recipeDAO.saveRecipe("Test Recipe", "This is a test recipe", "EASY", "VEGAN", 30, "test.jpg",
//        "This is a test instruction");
//
//    // Save the test ingredients to a recipe
//    ingredientDAO.saveIngredientToRecipe(1, testIngredient1.getId(), 2.0);
//    ingredientDAO.saveIngredientToRecipe(1, testIngredient2.getId(), 3.0);
//    ingredientDAO.saveIngredientToRecipe(1, testIngredient3.getId(), 4.0);
//    ingredientDAO.saveIngredientToRecipe(1, testIngredient4.getId(), 5.0);
//    ingredientDAO.saveIngredientToRecipe(1, testIngredient5.getId(), 6.0);
//  }
//
//  @AfterEach
//  public void tearDown() throws SQLException {
//    try (Connection conn = DriverManager.getConnection(Database.DB_URL, Database.USER,
//        Database.PASS);
//        Statement stmt = conn.createStatement()) {
//      stmt.execute(
//          // This will delete all tables and files associated with the database
//          "DROP ALL OBJECTS DELETE FILES");
//    }
//  }
//
//  // Test the save method in the ShoppingListDAO class
//  // This is done by generating a new shopping list based on the test recipe
//  // and saving it to the database. The method then retrieves the shopping list
//  // from the database and compares it to the original shopping list.
//  @Test
//  @DisplayName("")
//  public void testSaveShoppingList() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//    shoppingList.put(testIngredient3.getId(), 4.0);
//    shoppingList.put(testIngredient4.getId(), 5.0);
//    shoppingList.put(testIngredient5.getId(), 6.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertEquals(shoppingList, retrievedShoppingList,
//        "The saved shopping list should match the retrieved shopping list.");
//  }
//
//  @Test
//  public void testDeleteShoppingList() {
//    shoppingListDAO.deleteAllForUser(testUser.userId());
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertTrue(retrievedShoppingList.isEmpty(),
//        "The shopping list should be empty after deletion.");
//  }
//
//  @Test
//  public void testUpdateShoppingList() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> updatedShoppingList = new HashMap<>();
//    updatedShoppingList.put(testIngredient1.getId(), 4.0);
//    updatedShoppingList.put(testIngredient2.getId(), 5.0);
//
//    shoppingListDAO.save(updatedShoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertEquals(updatedShoppingList, retrievedShoppingList,
//        "The updated shopping list should match the retrieved shopping list.");
//  }
//
//  @Test
//  public void testGetShoppingList() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertEquals(shoppingList, retrievedShoppingList,
//        "The saved shopping list should match the retrieved shopping list.");
//  }
//
//  @Test
//  public void testGetEmptyShoppingList() {
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertTrue(retrievedShoppingList.isEmpty(),
//        "The shopping list should be empty if no shopping list has been saved.");
//  }
//
//  @Test
//  public void testGetShoppingListForNonExistingUser() {
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(1);
//
//    assertTrue(retrievedShoppingList.isEmpty(),
//        "The shopping list should be empty if the user does not exist.");
//  }
//
//  @Test
//  public void testSaveEmptyShoppingList() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertTrue(retrievedShoppingList.isEmpty(),
//        "The shopping list should be empty if no ingredients are added.");
//  }
//
//  @Test
//  @DisplayName("Test that the getRecipesInShoppingListForUser method"
//      + "returns the correct amount of recipes in the shopping list.")
//  public void testGetRecipesInShoppingListForUser() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//    shoppingList.put(testIngredient3.getId(), 4.0);
//    shoppingList.put(testIngredient4.getId(), 5.0);
//    shoppingList.put(testIngredient5.getId(), 6.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    assertEquals(1, shoppingListDAO.getRecipesInShoppingListForUser(testUser.userId()).size(),
//        "The amount of recipes in the shopping list "
//            + "should be equal to the amount of ingredients in the shopping list.");
//  }
//
//  @Test
//  @DisplayName("Test that the getAllIngredientsFromShoppingList method"
//      + "returns the correct amount of ingredients based on the recipes"
//      + "in the shopping list.")
//  public void testGetAllIngredientsFromShoppingList() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//    shoppingList.put(testIngredient3.getId(), 4.0);
//    shoppingList.put(testIngredient4.getId(), 5.0);
//    shoppingList.put(testIngredient5.getId(), 6.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    assertEquals(5, shoppingListDAO.getAllIngredientsFromShoppingList(testUser.userId()).size(),
//        "The amount of ingredients in the shopping list should be equal "
//            + "to the amount of ingredients in the shopping list in total.");
//  }
//
//  @Test
//  @DisplayName("Test that the getShoppingListForUser method works correctly.")
//  public void testGetShoppingListForUser() throws SQLException {
//    Map<Integer, Double> shoppingList = new HashMap<>();
//    shoppingList.put(testIngredient1.getId(), 2.0);
//    shoppingList.put(testIngredient2.getId(), 3.0);
//    shoppingList.put(testIngredient3.getId(), 4.0);
//    shoppingList.put(testIngredient4.getId(), 5.0);
//    shoppingList.put(testIngredient5.getId(), 6.0);
//
//    shoppingListDAO.save(shoppingList, testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertEquals(shoppingList, retrievedShoppingList,
//        "The saved shopping list should match the retrieved shopping list.");
//  }
//
//  @Test
//  @DisplayName("Test that the addRecipeToShoppingList method works correctly.")
//  public void testAddRecipeToShoppingList() throws SQLException {
//    shoppingListDAO.addRecipeToShoppingList(testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertEquals(2.0, retrievedShoppingList.get(testIngredient1.getId()),
//        "The amount of the first ingredient should be 2.0.");
//    assertEquals(3.0, retrievedShoppingList.get(testIngredient2.getId()),
//        "The amount of the second ingredient should be 3.0.");
//    assertEquals(4.0, retrievedShoppingList.get(testIngredient3.getId()),
//        "The amount of the third ingredient should be 4.0.");
//    assertEquals(5.0, retrievedShoppingList.get(testIngredient4.getId()),
//        "The amount of the fourth ingredient should be 5.0.");
//    assertEquals(6.0, retrievedShoppingList.get(testIngredient5.getId()),
//        "The amount of the fifth ingredient should be 6.0.");
//  }
//
//  @Test
//  @DisplayName("deleteRecipeFromShoppingList should delete the selected recipe.")
//  void testDeleteRecipeFromShoppingList() throws SQLException {
//    shoppingListDAO.addRecipeToShoppingList(testUser.userId(), 1);
//    shoppingListDAO.deleteRecipeFromShoppingList(testUser.userId(), 1);
//
//    Map<Integer, Double> retrievedShoppingList = shoppingListDAO.getShoppingListForUser(
//        testUser.userId());
//
//    assertTrue(retrievedShoppingList.isEmpty(),
//        "The shopping list should be empty after deletion.");
//  }
//
//  @Test
//  @DisplayName("getRecipesWithIngredientsInShoppingList should return list with size 1.")
//  void testGetRecipesWithIngredientsInShoppingList() throws SQLException {
//    shoppingListDAO.addRecipeToShoppingList(testUser.userId(), 1);
//
//    assertEquals(1,
//        shoppingListDAO.getRecipesWithIngredientsInShoppingList(testUser.userId()).size(),
//        "The amount of recipes in the shopping list should be 1.");
//  }
//
//  @Test
//  @DisplayName("getRecipesWithIngredientsInShoppingList should return an object"
//      + "with the correct information")
//  void testGetRecipesWithIngredientsInShoppingListWithCorrectInformation() throws SQLException {
//    shoppingListDAO.addRecipeToShoppingList(testUser.userId(), 1);
//
//    // Test that the name matches
//    assertEquals("Test Recipe",
//        shoppingListDAO.getRecipesWithIngredientsInShoppingList(testUser.userId()).get(0).getName(),
//        "The name of the recipe should be 'Test Recipe'.");
//
//    // Test that the description matches
//    assertEquals("This is a test recipe",
//        shoppingListDAO.getRecipesWithIngredientsInShoppingList(testUser.userId()).get(0)
//            .getDescription(),
//        "The description of the recipe should be 'This is a test recipe'.");
//
//    // Test that the list of ingredients matches
//    assertEquals(5,
//        shoppingListDAO.getRecipesWithIngredientsInShoppingList(testUser.userId()).get(0)
//            .getIngredients().size(),
//        "The amount of ingredients in the recipe should be 5.");
//  }
//}