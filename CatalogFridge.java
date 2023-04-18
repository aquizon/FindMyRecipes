import java.text.NumberFormat;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import javafx.scene.control.TextInputControl;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TablePosition;
import javafx.event.ActionEvent;
import java.lang.String;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Priority;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class CatalogFridge extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;
  
  // set the dimensions of the stage
  // private final int initWidth = 900;
  // private final int initHeight = 700;

  // create three TableView objects: Recipes, Fridge, Ingredient List
  private TableView<Recipe> recipeTable = new TableView<>();
  private TableView<Ingredient> fridgeTable = new TableView<>();
  private TableView<Ingredient> ingredientsTable = new TableView<>();

  // ObservableList objects to be associated with the TableView objects
  private ObservableList<Recipe> recipeData = FXCollections.observableArrayList();
  private ObservableList<Ingredient> fridgeData = FXCollections.observableArrayList();
  private ObservableList<Ingredient> ingredientsData = FXCollections.observableArrayList();

  // buttons/textFields for the Catalog Fridge Window
  TextField searchMasterField = new TextField();
  Button fruitsButton = new Button("Fruits");
  Button vegetablesButton = new Button("Vegetables");
  Button grainsButton = new Button("Grains");
  Button proteinsButton = new Button("Proteins");
  Button dairyButton = new Button("Dairy");
  Button otherButton = new Button("Other");

  // buttons for the bottom menu for Catalog Fridge Window
  Button favoritesRecipesButton = new Button("Favorites Button");
  Button generateRecipesButton = new Button("Generate Recipes");
  Button saveAndExitButton = new Button("Save and Exit");

  Button backButton = new Button("Back");
  GridPane mainPane = new GridPane(); // main container
  GridPane foodCategoriesPane = new GridPane();

  public void start(Stage stage) {

    HBox menuBarBox = new HBox(20); // contains the three buttons at the bottom
    menuBarBox.setAlignment(Pos.CENTER);
    menuBarBox.getChildren().addAll(favoritesRecipesButton, generateRecipesButton, saveAndExitButton);

    mainPane.setHgap(10);
    mainPane.setVgap(20);
    // gridpane.add(Node, colIndex, rowIndex, colSpan, rowSpan)
    mainPane.add(searchMasterField, 0, 0, 2, 1);
    mainPane.add(fridgeTable, 2, 0, 1, 4);
    mainPane.add(menuBarBox, 0, 4, 3, 1);

    foodCategoriesPane.add(fruitsButton, 0, 0);
    foodCategoriesPane.add(vegetablesButton, 1, 0);
    foodCategoriesPane.add(grainsButton, 0, 1);
    foodCategoriesPane.add(proteinsButton, 1, 1);
    foodCategoriesPane.add(dairyButton, 0, 2);
    foodCategoriesPane.add(otherButton, 1, 2);
    
    mainPane.add(foodCategoriesPane, 0, 1, 2, 3);
    setButtonHandlers();

    Scene scene = new Scene(mainPane);
    stage.setScene(scene);
    stage.setTitle("Find My Recipes");
    stage.show();
  }

  private void setButtonHandlers() {
    saveAndExitButton.setOnAction(e -> saveAndExitButtonHandler());
    fruitsButton.setOnAction(e -> fruitsButtonHandler());
    vegetablesButton.setOnAction(e -> vegetablesButtonHandler());
    grainsButton.setOnAction(e -> grainsButtonHandler());
    proteinsButton.setOnAction(e -> proteinsButtonHandler());
    dairyButton.setOnAction(e -> dairyButtonHandler());
    otherButton.setOnAction(e -> otherButtonHandler());
    backButton.setOnAction(e -> backButtonHandler());
  }

  private void saveAndExitButtonHandler() {
    Platform.exit();
  }

  private void fruitsButtonHandler() {
    switchToFilteredTableScene();
  }
  private void vegetablesButtonHandler() {
    switchToFilteredTableScene();
  }
  private void grainsButtonHandler() {
    switchToFilteredTableScene();
  }
  private void proteinsButtonHandler() {
    switchToFilteredTableScene();
  }
  private void dairyButtonHandler() {
    switchToFilteredTableScene();
  }
  private void otherButtonHandler() {
    switchToFilteredTableScene();
  }

  private void backButtonHandler() {
    mainPane.getChildren().remove(ingredientsTable);
    mainPane.getChildren().remove(backButton);
    mainPane.add(foodCategoriesPane, 0, 1, 2, 3);
  }

  private void switchToFilteredTableScene() {
    mainPane.getChildren().remove(foodCategoriesPane);
    mainPane.add(backButton, 0, 1);
    mainPane.add(ingredientsTable, 0, 2, 2, 2);
  }

  public static void main(String[] args) {
    launch(args);
  }
}