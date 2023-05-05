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
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text; 
import javafx.scene.input.MouseButton;
import javafx.beans.binding.Bindings;

public class GeneratedRecipes {
// public class GeneratedRecipes extends Application {
    private Stage stage;
    private Scene scene;
  
  // set the dimensions of the stage
  private final int initWidth = 925;
  private final int initHeight = 625;

  // create three TableView objects: Recipes, Fridge, Ingredient List
  private TableView<Recipe> recipesTable = new TableView<>();
  
  // private HashMap<String, ObservableList<Ingredient>> recipesMap = new HashMap<>();

  // ObservableList objects to be associated with the TableView objects
  private ObservableList<Recipe> recipesData = FXCollections.observableArrayList();;

  // buttons/textFields for the Generated Recipes Window
  Label title = new Label("FindMyRecipes");

  // buttons + Labels for the bottom menu for Generated Recipes Window
  heartButton favoritesRecipesButton;
  Label favoritesRecipesButtonLabel = new Label("Favorites");
  Button backToFridgeButton = new Button();
  Label backToFridgeButtonLabel = new Label("Back to Fridge");
  Button saveAndExitButton = new Button();
  Label saveAndExitButtonLabel = new Label("Save and Exit");

  BorderPane mainPane = new BorderPane(); // main container

  HBox menuBarBox = new HBox(125); // contains the three buttons at the bottom

  HBox center = new HBox(10);
  VBox recipeInfo = new VBox(20);
  ImageView recipePic;
  Text recipeIngredients = new Text();
  Text recipeInstructions = new Text();

  TableColumn<Recipe,Boolean> favoritedCol = new TableColumn<Recipe, Boolean>("Is Favorited");

  FavoritesList fList;

  // public void start(Stage stage) throws FileNotFoundException {
  //   Scene scene = generateGeneratedRecipesScene();
  //   stage.setScene(scene);
  //   stage.show();
  // }

  private void setUpMenuBarBox() {
    menuBarBox.setAlignment(Pos.CENTER);
    favoritesRecipesButton = new heartButton(true, 60, 60);
    favoritesRecipesButton.fillHeart();
    VBox favoritesBox = new VBox();
    favoritesBox.getChildren().addAll(favoritesRecipesButton.getHeart(), favoritesRecipesButtonLabel);

    Image img = new Image("./images/FridgeLogo.png");
    ImageView view = new ImageView(img);
    view.setFitHeight(50);
    view.setFitWidth(30);
    // view.setPreserveRatio(false);
    backToFridgeButton.setMaxSize(30, 50);
    backToFridgeButton.setMinSize(10, 5);
    backToFridgeButton.setGraphic(view);
    backToFridgeButton.setTranslateX(21);
    VBox backToFridgeBox = new VBox();
    backToFridgeBox.getChildren().addAll(backToFridgeButton, backToFridgeButtonLabel);
    VBox saveAndExitBox = new VBox();
    Image img2 = new Image("./images/saveAndExit.png");
    ImageView view2 = new ImageView(img2);
    view2.setFitHeight(50);
    view2.setFitWidth(40);
    // view2.setPreserveRatio(true);
    saveAndExitButton.setGraphic(view2);
    saveAndExitButton.setMaxSize(30, 50);
    saveAndExitButton.setMinSize(10, 5);
    saveAndExitButton.setTranslateX(20);
    saveAndExitBox.getChildren().addAll(saveAndExitButton, saveAndExitButtonLabel);
    menuBarBox.getChildren().addAll(favoritesBox, backToFridgeBox, saveAndExitBox);
    mainPane.setBottom(menuBarBox);
  }

  private void setRecipesTableColumns() {
    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    // TableColumn favoritedCol = new TableColumn("Is Favorited");
    favoritedCol.setCellValueFactory(new PropertyValueFactory<>("isFavorited"));
    // favoritedCol.setCellFactory(cellFactory);
    // favoritedCol.setCellFactory(col -> {
    //     TableCell<Recipe, Boolean> cell = new TableCell<>();
    //     cell.itemProperty().addListener((obs, old, newVal) -> {
    //         if (newVal != null) {
    //           Node hb = createHeartGraphic(newVal);
    //           cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(hb));
    //         }
    //     });
    //     return cell;
    // });



    recipesTable.getColumns().addAll(nameCol);
    addButtonToRecipesTable();
  }

  private Node createHeartGraphic(Boolean isFavorited){
    if(isFavorited){
      heartButton hb = new heartButton(true, 20, 20);
      return hb.getHeart();
    }
    else {
      heartButton hb = new heartButton(false, 20, 20);
      return hb.getHeart();
    }
}

  private void addButtonToRecipesTable() {
      // TableColumn<Recipe, Void> colBtn = new TableColumn("");
      // colBtn.setStyle( "-fx-alignment: CENTER;");
      Callback<TableColumn<Recipe, Boolean>, TableCell<Recipe, Boolean>> cellFactory = new Callback<TableColumn<Recipe, Boolean>, TableCell<Recipe, Boolean>>() {
          @Override
          public TableCell<Recipe, Boolean> call(TableColumn<Recipe, Boolean> param) {
              TableCell<Recipe, Boolean> cell = new TableCell<Recipe, Boolean>() {
                  private heartButton hb = new heartButton(false, 20, 20);
                  private Button btn = hb.getHeart();
                  {
                      btn.setOnAction((ActionEvent e) -> {
                          Recipe selectedRecipe = getTableView().getItems().get(getIndex());
                          clickHeartButtonHandler(hb, selectedRecipe);

                      });
                  }
                  @Override
                  public void updateItem(Boolean item, boolean empty) {
                      super.updateItem(item, empty);
                      if (!empty) {
                        if (item) {
                            hb.fillHeart();
                        } else {
                            hb.emptyHeart();
                        }
                        setGraphic(btn);
                      }
                      else {
                        setGraphic(null);
                      }
                  }
              };
            return cell;
          }
      };
      // colBtn.setCellFactory(cellFactory);
      // recipesTable.getColumns().add(colBtn);
      favoritedCol.setCellFactory(cellFactory);
      recipesTable.getColumns().add(favoritedCol);
  }

  private void setButtonHandlers() {
    saveAndExitButton.setOnAction(e -> saveAndExitButtonHandler());
  }

  // Handler Methods
  private void saveAndExitButtonHandler() {
    Platform.exit();
  }

  private void recipeSelectedHandler(Recipe r) throws FileNotFoundException{
    Image image = new Image(r.getImgFname());
    recipePic.setFitHeight(100);
    recipePic.setFitWidth(100);
    recipePic.setImage(image);

    Label recipeName = new Label(r.getName());
    recipeName.setFont(new Font(14));

    Text ingredientText = new Text("Ingredients: ");

    TextArea ingredientBox = new TextArea();
    ingredientBox.setPrefWidth(250);
    ArrayList<String> ingredients = r.getIngredientWithQuantities();
    String toBuild = ""; 
    for (String s : ingredients){ 
        toBuild = toBuild + "- " + s + "\n"; 
    }
    ingredientBox.setText(toBuild);

    Text instructionsText = new Text("Instructions: ");

    TextArea instructionBox = new TextArea();
    instructionBox.setPrefWidth(250);
    ArrayList<String> instructions = r.getInstructions(); 
    String instructionBuilder = "";
    for (String s : instructions){
        instructionBuilder = instructionBuilder + "- " + s + "\n";
    }
    instructionBox.setText(instructionBuilder);

    recipeInfo.getChildren().clear();
    recipeInfo.getChildren().addAll(recipePic, recipeName, ingredientText, ingredientBox, instructionsText, instructionBox); 
    recipeInfo.setPadding(new Insets(0,10,0,0));
  }

  private void clickHeartButtonHandler(heartButton heart, Recipe selectedRecipe) {
    System.out.println(selectedRecipe);
    if (heart.getIsFilled()) {
      heart.emptyHeart();
      heart.setIsFilled(false);
      fList.removeRecipe(selectedRecipe);
      selectedRecipe.setIsFavorited(false);
    }
    else {
      heart.fillHeart();
      heart.setIsFilled(true);
     fList.addRecipe(selectedRecipe);
     selectedRecipe.setIsFavorited(true);
    }
  }

  public void fillRecipesData(ObservableList<Recipe> rList, ArrayList<String> fridge) { 
    //first get the fridge itself, then I want to iterate over the recipe list and check if its in the fridge? if they all match add to the rList. 
   
    for (Recipe r : rList){ 
      boolean flag = true;
      ArrayList<String> ingredientNoQuantities = r.getIngredientNoQuantities();
      for (String i : ingredientNoQuantities) {
        if (!fridge.contains(i)) {
          flag = false;
        }
      }
      if (flag) {
        recipesData.add(r);
      }
    }
    
  }

  private void setUpRecipesTable() {
    // set up Recipes Table
    recipesTable.setPrefSize(350, 250);
    recipesTable.setItems(recipesData); //replaced recipesData here with the list of recipes that fit the fridgeData. 
    setRecipesTableColumns();

    recipesTable.setOnMouseClicked((MouseEvent event) -> {
      if(event.getButton().equals(MouseButton.PRIMARY)){
          Recipe currRecipe = recipesTable.getSelectionModel().getSelectedItem();
          try {
            recipeSelectedHandler(currRecipe);
          } catch(FileNotFoundException e) {
            e.printStackTrace();
          }
      }
    });
  }

  public Scene generateGeneratedRecipesScene(FavoritesList f, ObservableList<Recipe> rList) throws FileNotFoundException {
    fList = f;
    // System.out.println("Fridge Data: " + String.join(", ", fridge));
    // recipesData = rList;
    setUpMenuBarBox();
    mainPane.setTop(title);
    mainPane.setAlignment(title, Pos.CENTER);
    title.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 30;");

    setUpRecipesTable();

    //set up Recipe Info Section
    Image image = new Image(new FileInputStream("./images/GenerateRecipes.png")); //to fix later 
    recipePic = new ImageView(image); 
    recipePic.setFitHeight(125);
    recipePic.setFitWidth(125);
    recipeIngredients.setText("Recipe Ingredients Here");
    recipeInstructions.setText("Recipe Instructions Here");
    recipeInfo.getChildren().addAll(recipePic, recipeIngredients, recipeInstructions);
    recipeInfo.setPrefSize(550, 400);
    recipeInfo.setAlignment(Pos.TOP_CENTER);

    center.setPadding(new Insets(10, 0, 10, 0));
    center.getChildren().addAll(recipesTable, recipeInfo);

    mainPane.setCenter(center);

    setButtonHandlers();
    mainPane.setBackground(new Background(new BackgroundFill(Color.web("#FFEEDF"), null, null)));

    return new Scene(mainPane, initWidth, initHeight);
  }

  // public static void main(String[] args) {
  //   launch(args);
  // }
}