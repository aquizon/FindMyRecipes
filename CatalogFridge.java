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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

// public class CatalogFridge {
public class CatalogFridge{
    private Stage stage;
    private Scene scene;
  
  // set the dimensions of the stage
  private final static int initWidth = 925;
  private final static int initHeight = 625;

  private final static int categoryWidth = 400;
  private final static int categoryHeight = 300;

  // create three TableView objects: Recipes, Fridge, Ingredient List
  private TableView<Ingredient> fridgeTable = new TableView<>();
  private TableView<Ingredient> ingredientsTable = new TableView<>();
  
  private HashMap<String, ObservableList<Ingredient>> ingredientCategories = new HashMap<>();

  // ObservableList objects to be associated with the TableView objects
  private ObservableList<Ingredient> fridgeData = FXCollections.observableArrayList();
  private ArrayList<String> fridgeDataNames = new ArrayList<String>();
  private ObservableList<Ingredient> ingredientsData = FXCollections.observableArrayList();
  //private ObservableList<Recipe> recipesData = FXCollections.observableArrayList();
  private ObservableList<Ingredient> currIngredientList;

  // buttons/textFields for the Catalog Fridge Window
  Label title = new Label("FindMyRecipes");
  TextField searchBox = new TextField();
  Button fruitsButton = new Button("Fruits");
  Button vegetablesButton = new Button("Vegetables");
  Button grainsButton = new Button("Grains");
  Button proteinsButton = new Button("Proteins");
  Button dairyButton = new Button("Dairy");
  Button otherButton = new Button("Other");

  // buttons + Labels for the bottom menu for Catalog Fridge Window
  Button favoritesRecipesButton = new Button();
  Label favoritesRecipesButtonLabel = new Label("  Favorites");
  Button generateRecipesButton = new Button();
  Label generateRecipesButtonLabel = new Label("Generate Recipes");
  Button saveAndExitButton = new Button();
  Label saveAndExitButtonLabel = new Label("Save and Exit");
  Label ingredientCatalogLabel = new Label("Ingredient Catalog");
  Label fridgeLabel = new Label("My Fridge");

  Button backButton = new Button("Back");
  GridPane mainPane = new GridPane(); // main container
  GridPane foodCategoriesPane = new GridPane();

  HBox menuBarBox = new HBox(125); // contains the three buttons at the bottom

  String currWindow;

  private void setSearchBarHandler() {
    searchBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
          if (ke.getCode().equals(KeyCode.ENTER)) {
              searchBarHandler(searchBox.getText());
          }
      }
    });
  }

  private void setUpMenuBarBox() {
    menuBarBox.setAlignment(Pos.CENTER);
    favoritesRecipesButton.setStyle("-fx-background-color: red; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
    favoritesRecipesButton.setPrefSize(60, 60);
    VBox favoritesBox = new VBox();
    favoritesBox.getChildren().addAll(favoritesRecipesButton, favoritesRecipesButtonLabel);

    Image img = new Image("./images/GenerateRecipes.png");
    ImageView view = new ImageView(img);
    view.setFitHeight(60);
    view.setPreserveRatio(true);
    generateRecipesButton.setPrefSize(60, 60);
      //Setting a graphic to the button
      generateRecipesButton.setGraphic(view);
      generateRecipesButton.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 65px; " +
            "-fx-min-height: 65px; " +
            "-fx-max-width: 65px; " +
            "-fx-max-height: 65px;"
      );
    generateRecipesButton.setTranslateX(17);
    VBox generateRecipesBox = new VBox();
    generateRecipesBox.getChildren().addAll(generateRecipesButton, generateRecipesButtonLabel);
    VBox saveAndExitBox = new VBox();
    Image img2 = new Image("./images/saveAndExit.png");
    ImageView view2 = new ImageView(img2);
    view2.setFitHeight(60);
    view2.setFitWidth(50);
    // view2.setPreserveRatio(true);
    saveAndExitButton.setGraphic(view2);
    saveAndExitButton.setMaxSize(40, 60);
    saveAndExitButton.setMinSize(10, 5);
    saveAndExitButton.setTranslateX(17);
    saveAndExitBox.getChildren().addAll(saveAndExitButton, saveAndExitButtonLabel);
    menuBarBox.getChildren().addAll(favoritesBox, generateRecipesBox, saveAndExitBox);
    mainPane.add(menuBarBox, 0, 6, 3, 1);
  }

  private void setUpFoodCategories() {
    fruitsButton.setPrefSize(275, 150);
    fruitsButton.setStyle("-fx-background-color: #ED220D;" + "-fx-text-fill: #FFFFFF");
    vegetablesButton.setPrefSize(275, 150);
    vegetablesButton.setStyle("-fx-background-color: #60D938;" + "-fx-text-fill: #FFFFFF");
    grainsButton.setPrefSize(275, 150);
    grainsButton.setStyle("-fx-background-color: #FEAE00;" + "-fx-text-fill: #FFFFFF");
    proteinsButton.setPrefSize(275, 150);
    proteinsButton.setStyle("-fx-background-color: #7B2CDF;" + "-fx-text-fill: #FFFFFF");
    dairyButton.setPrefSize(275, 150);
    dairyButton.setStyle("-fx-background-color: #00A1FF;" + "-fx-text-fill: #FFFFFF");
    otherButton.setPrefSize(275, 150);
    otherButton.setStyle("-fx-background-color: #929292;" + "-fx-text-fill: #FFFFFF");
    foodCategoriesPane.add(fruitsButton, 0, 0);
    foodCategoriesPane.add(vegetablesButton, 1, 0);
    foodCategoriesPane.add(grainsButton, 0, 1);
    foodCategoriesPane.add(proteinsButton, 1, 1);
    foodCategoriesPane.add(dairyButton, 0, 2);
    foodCategoriesPane.add(otherButton, 1, 2);
  }

  private void setIngredientTableColumns() {
    // TableColumn idCol = new TableColumn("Id");
    // idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
 
    // TableColumn nameCol = new TableColumn("Name");
    // nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    ingredientsTable.setStyle("-fx-table-cell-border-color: transparent;");
    TableColumn imgCol = new TableColumn();
    imgCol.setCellValueFactory(new PropertyValueFactory<>("img"));

    ingredientsTable.getColumns().addAll(imgCol);
    //addImageToIngredientTable();
    addButtonToIngredientTable();
    imgCol.prefWidthProperty().bind(ingredientsTable.widthProperty().multiply(0.3));
    imgCol.setResizable(false);
  }

  private void addButtonToIngredientTable() {
      TableColumn<Ingredient, String> colBtn = new TableColumn("");
      colBtn.setCellValueFactory(new PropertyValueFactory<>("name"));
      colBtn.setStyle( "-fx-alignment: CENTER;");
      Callback<TableColumn<Ingredient, String>, TableCell<Ingredient, String>> cellFactory = new Callback<TableColumn<Ingredient, String>, TableCell<Ingredient, String>>() {
          @Override
          public TableCell<Ingredient, String> call(final TableColumn<Ingredient, String> param) {
              final TableCell<Ingredient, String> cell = new TableCell<Ingredient, String>() {
                  private final Button btn = new Button("Add to Fridge");
                  {
                      btn.setOnAction((ActionEvent e) -> {
                          Ingredient t = getTableView().getItems().get(getIndex());
                          addtoFridgeHandler(t);
                      });
                  }
                  @Override
                  public void updateItem(String item, boolean empty) {
                      super.updateItem(item, empty);
                      if (empty) {
                          setGraphic(null);
                      } else {
                        VBox container = new VBox();
                        Label name = new Label();
                        name.setText(item);
                        container.getChildren().addAll(name,btn);
                        setGraphic(container);
                      }
                  }
              };
              return cell;
          }
      };
      colBtn.setCellFactory(cellFactory);
      ingredientsTable.getColumns().add(colBtn);
      colBtn.prefWidthProperty().bind(ingredientsTable.widthProperty().multiply(0.7));
      colBtn.setResizable(false);
  }

  private void setFridgeTableColumns() {
    // TableColumn idCol = new TableColumn("Id");
    // idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
 
    // TableColumn nameCol = new TableColumn("Name");
    // nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    fridgeTable.setStyle("-fx-table-cell-border-color: transparent;");
    TableColumn imgCol = new TableColumn();
    imgCol.setCellValueFactory(new PropertyValueFactory<>("imgFridge"));
    //addImageToFridgeTable();
    fridgeTable.getColumns().addAll(imgCol);
    addButtonToFridgeTable();
    imgCol.prefWidthProperty().bind(fridgeTable.widthProperty().multiply(0.3));
    imgCol.setResizable(false);
  }
  
  private void addButtonToFridgeTable() {
      TableColumn<Ingredient, String> colBtn = new TableColumn("");
      colBtn.setCellValueFactory(new PropertyValueFactory<>("name"));
      colBtn.setStyle( "-fx-alignment: CENTER;");
      Callback<TableColumn<Ingredient, String>, TableCell<Ingredient, String>> cellFactory = new Callback<TableColumn<Ingredient, String>, TableCell<Ingredient, String>>() {
          @Override
          public TableCell<Ingredient, String> call(final TableColumn<Ingredient, String> param) {
              final TableCell<Ingredient, String> cell = new TableCell<Ingredient, String>() {
                  private final Button btn = new Button("Remove");
                  {
                      btn.setOnAction((ActionEvent e) -> {
                          Ingredient t = getTableView().getItems().get(getIndex());
                          removeFromFridgeHandler(t);
                      });
                  }
                  @Override
                  public void updateItem(String item, boolean empty) {
                      super.updateItem(item, empty);
                      if (empty) {
                          setGraphic(null);
                      } else {
                        VBox container = new VBox();
                        Label name = new Label();
                        name.setText(item);
                        container.getChildren().addAll(name,btn);
                        setGraphic(container);
                      }
                  }
              };
              return cell;
          }
      };
      colBtn.setCellFactory(cellFactory);
      fridgeTable.getColumns().add(colBtn);
      colBtn.prefWidthProperty().bind(fridgeTable.widthProperty().multiply(0.7));
      colBtn.setResizable(false);
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

  // Handler Methods
  private void saveAndExitButtonHandler() {
    Platform.exit();
  }

  private void fruitsButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Fruits");
    ingredientsTable.setItems(currIngredientList);
  }

  private void vegetablesButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Vegetables");
    ingredientsTable.setItems(currIngredientList);
  }
  private void grainsButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Grains");
    ingredientsTable.setItems(currIngredientList);
  }
  private void proteinsButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Proteins");
    ingredientsTable.setItems(currIngredientList);
  }
  private void dairyButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Dairy");
    ingredientsTable.setItems(currIngredientList);
  }
  private void otherButtonHandler() {
    switchToFilteredTableScene();
    currWindow = "Filtered";
    currIngredientList = ingredientCategories.get("Other");
    ingredientsTable.setItems(currIngredientList);
  }

  private void backButtonHandler() {
    mainPane.getChildren().remove(ingredientsTable);
    mainPane.getChildren().remove(backButton);
    currIngredientList = ingredientsData;
    currWindow = "Categories";
    mainPane.add(foodCategoriesPane, 0, 3, 2, 3);
  }

  private void addtoFridgeHandler(Ingredient t) {
    if (!fridgeData.contains(t)) {
      fridgeData.add(t);
      fridgeDataNames.add(t.getName());
    }
  }

  private void removeFromFridgeHandler(Ingredient t) {
    fridgeData.remove(t);
    fridgeDataNames.remove(t.getName());
  }

  private void searchBarHandler(String searchText) {
    if (currWindow == "Categories") {
      switchToFilteredTableScene();
    }
    ingredientsTable.setItems(filterList(searchText));
    searchBox.clear();
  }

  private void switchToFilteredTableScene() {
    mainPane.getChildren().remove(foodCategoriesPane);
    mainPane.add(backButton, 0, 3);
    mainPane.add(ingredientsTable, 0, 4, 2, 2);
  }

  public void setIngredientCategories(HashMap<String, ObservableList<Ingredient>> t) {
    ingredientCategories = t;
  }

  public void setIngredientsData(ObservableList<Ingredient> t) {
    ingredientsData = t;
  }

  private ObservableList<Ingredient> filterList(String searchText) {
    List<Ingredient> filteredList = new ArrayList<>();
    for (Ingredient t : currIngredientList) { 
      if (searchFindsIngredient(t, searchText)) {
        filteredList.add(t);
      }
    }
    ObservableList<Ingredient> newList = FXCollections.observableList(filteredList);
    return newList;
  }

  private boolean searchFindsIngredient(Ingredient t, String searchText) {
    return (t.getName().toLowerCase().contains(searchText.toLowerCase())) ||
           Integer.valueOf(t.getId()).toString().equals(searchText.toLowerCase());
  }

  public ArrayList<String> getFridgeDataNames() {
    return fridgeDataNames;
  }


  public Scene generateCatalogFridgeScene() {
    currWindow = "Categories";
    setUpMenuBarBox();
    setSearchBarHandler();
    mainPane.setHgap(10);
    mainPane.setVgap(15);
    // gridpane.add(Node, colIndex, rowIndex, colSpan, rowSpan)
    mainPane.add(title, 0, 0, 3, 1);
    mainPane.setHalignment(title, HPos.CENTER);
    title.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 30;");
    mainPane.add(ingredientCatalogLabel, 0, 1, 2, 1);
    mainPane.setHalignment(ingredientCatalogLabel, HPos.CENTER);
    ingredientCatalogLabel.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 20;");
    mainPane.add(fridgeLabel, 2, 1);
    mainPane.setHalignment(fridgeLabel, HPos.CENTER);
    fridgeLabel.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 20;");

    searchBox.setPromptText("Search for Ingredient: ");
    mainPane.add(searchBox, 0, 2, 2, 1);

    fridgeTable.setPrefSize(350, 400);
    mainPane.add(fridgeTable, 2, 2, 1, 4);
    
    setUpFoodCategories();
    mainPane.add(foodCategoriesPane, 0, 3, 2, 3);

    // set up Fridge Table
    fridgeTable.setItems(fridgeData);
    setFridgeTableColumns();

    // set up Ingredients Table
    ingredientsTable.setPrefSize(550, 400);
    ingredientsTable.setItems(ingredientsData);
    setIngredientTableColumns();
    // loadIngredientsFromFile();
    currIngredientList = ingredientsData;

    setButtonHandlers();
    mainPane.setBackground(new Background(new BackgroundFill(Color.web("#FFEEDF"), null, null)));

    return new Scene(mainPane, initWidth, initHeight);

  }
}