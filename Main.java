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
      Scene grScene = GeneratedRecipes.generateGeneratedRecipesScene();
      Scene cfScene = CatalogFridge.generateCatalogFridgeScene();
      CatalogFridge.generateRecipesButton.setOnAction(e -> stage.setScene(grScene));
      GeneratedRecipes.backToFridgeButton.setOnAction(e -> stage.setScene(cfScene));
      stage.setScene(cfScene);
      stage.show();
    }
}