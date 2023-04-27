import javax.xml.catalog.Catalog;

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
 
 
public class Main extends Application  {
    public static void main(String args[]){          
         launch(args);     
    } 
         
    @Override    
    public void start(Stage stage) throws Exception { 
      Scene cfScene = CatalogFridge.generateCatalogFridgeScene();
      Scene grScene = GeneratedRecipes.generateGeneratedRecipesScene();
      Scene frScene = Favorites.generateFavoritesScene();

      //Changes scenes from the catalog fridge scene
      CatalogFridge.generateRecipesButton.setOnAction(e -> stage.setScene(grScene));
      CatalogFridge.favoritesRecipesButton.setOnAction(e -> stage.setScene(frScene));
      
      //changes scenes from the generatedRecipes scene
      GeneratedRecipes.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
      GeneratedRecipes.favoritesRecipesButton.setOnAction(e -> stage.setScene(frScene));

      //changes scenes from the favorites scene
      Favorites.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
      Favorites.generateRecipesButton.setOnAction(e -> stage.setScene(grScene));
      stage.setScene(cfScene);
      stage.show();
    }
}