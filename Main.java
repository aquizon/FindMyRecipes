import javax.xml.catalog.Catalog;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;
import javafx.application.Platform;

public class Main extends Application {
  CatalogFridge fridge = new CatalogFridge();
  GeneratedRecipes recipes = new GeneratedRecipes();
  Favorites favorites = new Favorites();

  int fruitsCount = 0;
  int vegetablesCount = 0;
  int grainsCount = 0;
  int proteinsCount = 0;
  int dairyCount = 0;
  int otherCount = 0;

  private final String recipeFname = "Recipes_Dataset_Modified.csv";
  String CsvFile = "RecipeIngredientsDataset.csv";
  RecipeData rd = new RecipeData(recipeFname);
  private Map<String, Recipe> recipeMap = rd.getRecipeMap();
  private ObservableList<Recipe> recipesData = rd.getRecipesData();
  private HashMap<String, ObservableList<Ingredient>> ingredientCategories = new HashMap<>();
  private ObservableList<Ingredient> ingredientsData = FXCollections.observableArrayList();
  private FavoritesList fList = new FavoritesList();

  public static void main(String args[]) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    loadIngredientsFromFile();
    loadData(); // this updates the fridge and favorites data if there is
    // anything in the data
    // file
    // UPDATE COUNTS!!!
    seedFavoritesList();
    Scene cfScene = fridge.generateCatalogFridgeScene();
    cfScene.getStylesheets().add(getClass().getResource("tableformat.css").toExternalForm());
    Scene grScene = recipes.generateGeneratedRecipesScene(fList, recipesData);
    grScene.getStylesheets().add(getClass().getResource("tableformat.css").toExternalForm());
    Scene frScene = favorites.generateFavoritesScene(fList);
    frScene.getStylesheets().add(getClass().getResource("tableformat.css").toExternalForm());
    fridge.setIngredientCategories(ingredientCategories);
    fridge.setIngredientsData(ingredientsData);

    // Changes scenes from the catalog fridge scene
    fridge.generateRecipesButton.setOnAction(e -> {
      recipes.fillRecipesData(recipeMap, recipesData, fridge.getFridgeDataNames());
      stage.setScene(grScene);
    });
    fridge.favoritesRecipesButton.setOnAction(e -> stage.setScene(frScene));

    fridge.saveAndExitButton.setOnAction(e -> {
      //System.out.println("on main");
      saveData();
      Platform.exit();
    });

    // changes scenes from the generatedRecipes scene
    recipes.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
    recipes.favoritesRecipesButton.getHeart().setOnAction(e -> {
      recipes.fillRecipesData(recipeMap, recipesData, fridge.getFridgeDataNames());
      stage.setScene(frScene);
    });

    recipes.saveAndExitButton.setOnAction(e -> {
      //System.out.println("on main");
      saveData();
      Platform.exit();
    });


    // changes scenes from the favorites scene
    favorites.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
    favorites.generateRecipesButton.setOnAction(e -> {
      recipes.fillRecipesData(recipeMap, recipesData, fridge.getFridgeDataNames());
      stage.setScene(grScene);
    });
    recipes.saveAndExitButton.setOnAction(e -> {
      //System.out.println("on main");
      saveData();
      Platform.exit();
    });

    stage.setScene(cfScene);
    stage.setResizable(false);
    stage.show();
  }

  private void addTestRecipes() {
    recipesData.add(recipeMap.get("Waldorf Salad"));
  }

  private void seedFavoritesList() {
    for (Recipe r : recipesData) {
      if (r.getIsFavorited()) {
        fList.addRecipe(r);
      }
    }
  }

  public void saveData() {
    // need the favorites list and fridge data
    ArrayList<String> fridgeIngredients = fridge.getFridgeDataNames();
    ObservableList<Recipe> favoriteRecipes = favorites.getFavoriteRecipes().getFavoritesList();

    System.out.println("fridge: " + fridgeIngredients);

    FindMyRecipesFileHandler.saveFridge(fridgeIngredients);
    // FindMyRecipesFileHandler.saveFavorites(favoriteRecipes);
  }

  public void loadData() {
    // call readRecords, only updating fridgeDataNames and the ObservableList of
    // favorited recipes

    ArrayList<String> fridgeDataNames = FindMyRecipesFileHandler.readFridgeData();
    ArrayList<String> favoriteRecipeNames = new ArrayList<>(); // will need to convert to ObservableList<Recipe>
    FavoritesList fList; // basically favorites.getFavoriteRecipes()

    System.out.println("Main fridge: " + fridgeDataNames);

    ObservableList<Ingredient> tempFridgeData = FXCollections.observableArrayList();

    if (fridgeDataNames != null) {

      for (String ingredientName : fridgeDataNames) {
        // access ingredientData
        for (Ingredient ingredient : ingredientsData) {
          if (ingredient.getName().equals(ingredientName)) {
            tempFridgeData.add(ingredient);

          }
        }
      }

      // System.out.println("updated tempFridgeData");

      // get the category and update count
      for (Ingredient ingredient : tempFridgeData) {
        // iterate through each category list to identify current ingredient's category
        // and update count
        for (Ingredient categoryIngredient : ingredientCategories.get("Fruits")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            fruitsCount++;
          }
        }
        for (Ingredient categoryIngredient : ingredientCategories.get("Vegetables")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            vegetablesCount++;
          }
        }
        for (Ingredient categoryIngredient : ingredientCategories.get("Grains")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            grainsCount++;
          }
        }
        for (Ingredient categoryIngredient : ingredientCategories.get("Proteins")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            proteinsCount++;
          }
        }
        for (Ingredient categoryIngredient : ingredientCategories.get("Dairy")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            dairyCount++;
          }
        }
        for (Ingredient categoryIngredient : ingredientCategories.get("Other")) {
          if (ingredient.getName().equals(categoryIngredient.getName())) {
            otherCount++;
          }
        }
      }
    }

    fridge.setFruitsCount(fruitsCount);
    fridge.setVegetablesCount(vegetablesCount);
    fridge.setGrainsCount(grainsCount);
    fridge.setProteinsCount(proteinsCount);
    fridge.setDairyCount(dairyCount);
    fridge.setOtherCount(otherCount);

    // set fridgeData and fridgeDataNames in CatalogFridge
    if (tempFridgeData != null) {
      fridge.setFridgeData(tempFridgeData);
      fridge.setFridgeDataNames(fridgeDataNames);
    }

  }

  private void loadIngredientsFromFile() {

    CsvFile = "RecipeIngredientsDataset.csv";
    String FieldDelimiter = ",";

    BufferedReader br;

    try {
      br = new BufferedReader(new FileReader(CsvFile));
      String line;
      br.readLine(); // Read first line cause they're column headers
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(FieldDelimiter, -1);

        Ingredient record = new Ingredient(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]); // lemons
                                                                                                          // potatos
                                                                                                          // tomatos
                                                                                                          // missing
                                                                                                          // image.
                                                                                                          // sirloin
                                                                                                          // beef top
        ingredientsData.add(record);

        // Add to hashmap
        String category = fields[2];
        ObservableList<Ingredient> categoryData;
        if (ingredientCategories.containsKey(category)) {
          categoryData = ingredientCategories.get(category);
        } else {
          categoryData = FXCollections.observableArrayList();
        }
        categoryData.add(record);
        ingredientCategories.put(category, categoryData);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(CatalogFridge.class.getName())
          .log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(CatalogFridge.class.getName())
          .log(Level.SEVERE, null, ex);
    }
  }
}