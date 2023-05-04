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
 
 
public class Main extends Application  {
  CatalogFridge fridge = new CatalogFridge();
  GeneratedRecipes recipes = new GeneratedRecipes();
  Favorites favorites = new Favorites();

  private ObservableList<Recipe> recipesData = FXCollections.observableArrayList();
  private HashMap<String, ObservableList<Ingredient>> ingredientCategories = new HashMap<>();
  private ObservableList<Ingredient> ingredientsData = FXCollections.observableArrayList();
  private FavoritesList fList = new FavoritesList();

    public static void main(String args[]){          
         launch(args);     
    } 
         
    @Override    
    public void start(Stage stage) throws Exception { 
      loadIngredientsFromFile();
      // loadRecipesFromFile();
      Scene cfScene = fridge.generateCatalogFridgeScene();
      // Scene grScene = recipes.getScene(); //.generateGeneratedRecipesScene();
      Scene grScene = recipes.generateGeneratedRecipesScene(fList);
      Scene frScene = favorites.generateFavoritesScene(fList);
      fridge.setIngredientCategories(ingredientCategories);
      fridge.setIngredientsData(ingredientsData);

      //Changes scenes from the catalog fridge scene
      fridge.generateRecipesButton.setOnAction(e -> stage.setScene(grScene));
      fridge.favoritesRecipesButton.setOnAction(e -> stage.setScene(frScene));
      
      //changes scenes from the generatedRecipes scene
      recipes.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
      recipes.favoritesRecipesButton.getHeart().setOnAction(e -> stage.setScene(frScene));

      //changes scenes from the favorites scene
      favorites.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
      favorites.generateRecipesButton.setOnAction(e -> stage.setScene(grScene));
      stage.setScene(cfScene);
      stage.show();
    }

    private void loadIngredientsFromFile() {
 
      String CsvFile = "Ingredients_Dataset.csv";
      String FieldDelimiter = ",";
  
      BufferedReader br;
  
      try {
          br = new BufferedReader(new FileReader(CsvFile));
          String line;
          br.readLine(); // Read first line cause they're column headers
          while ((line = br.readLine()) != null) {
              String[] fields = line.split(FieldDelimiter, -1);
  
              Ingredient record = new Ingredient(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
              ingredientsData.add(record);
              // Add to hashmap
              String category = fields[2];
              ObservableList<Ingredient> categoryData;
              if (ingredientCategories.containsKey(category)) {
                categoryData = ingredientCategories.get(category);
              }
              else {
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

  //   private void loadRecipesFromFile() {
  
  //     String CsvFile = "Recipes_Dataset_Modified.csv";
  //     String FieldDelimiter = ",";

  //     BufferedReader br;

  //     try {
  //         br = new BufferedReader(new FileReader(CsvFile));
  //         String line;
  //         br.readLine(); // Read first line cause they're column headers
  //         while ((line = br.readLine()) != null) {
  //             String[] fields = line.split(FieldDelimiter, -1);

  //             Recipe record = new Recipe(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4]);
  //             ingredientsData.add(record);
  //             // Add to hashmap
  //             String category = fields[2];
  //             ObservableList<Ingredient> categoryData;
  //             if (ingredientCategories.containsKey(category)) {
  //               categoryData = ingredientCategories.get(category);
  //             }
  //             else {
  //               categoryData = FXCollections.observableArrayList();
  //             }
  //             categoryData.add(record);
  //             ingredientCategories.put(category, categoryData);
  //         }
  //   } catch (FileNotFoundException ex) {
  //       Logger.getLogger(CatalogFridge.class.getName())
  //               .log(Level.SEVERE, null, ex);
  //   } catch (IOException ex) {
  //       Logger.getLogger(CatalogFridge.class.getName())
  //               .log(Level.SEVERE, null, ex);
  //     }
  // }
     //Commented this out temporarily so as to avoid problems but in my thought so we dont have to grab the recipes all the time we just load it on main? 
}