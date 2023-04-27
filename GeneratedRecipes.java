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

// public class GeneratedRecipes {
public class GeneratedRecipes extends Application {
    private Stage stage;
    private Scene scene;
  
  // set the dimensions of the stage
  private final int initWidth = 675;
  private final int initHeight = 500;

  // create three TableView objects: Recipes, Fridge, Ingredient List
  private TableView<Recipe> recipesTable = new TableView<>();
  
  // private HashMap<String, ObservableList<Ingredient>> recipesMap = new HashMap<>();

  // ObservableList objects to be associated with the TableView objects
  private ObservableList<Recipe> recipesData = FXCollections.observableArrayList();

  // buttons/textFields for the Generated Recipes Window
  Label title = new Label("FindMyRecipes");

  // buttons + Labels for the bottom menu for Generated Recipes Window
  heartButton favoritesRecipesButton;
  Label favoritesRecipesButtonLabel = new Label("Favorites");
  Button backToFridgeButton = new Button();
  Label backToFridgeButtonLabel = new Label("Back to Fridge");
  Button saveAndExitButton = new Button("Exit Image Here");
  Label saveAndExitButtonLabel = new Label("Save and Exit");

  BorderPane mainPane = new BorderPane(); // main container

  HBox menuBarBox = new HBox(100); // contains the three buttons at the bottom

  HBox center = new HBox(10);
  VBox recipeInfo = new VBox(20);
  ImageView recipePic;
  Text recipeIngredients = new Text();
  Text recipeInstructions = new Text();

  public void start(Stage stage) throws FileNotFoundException {
    Scene scene = generateGeneratedRecipesScene();
    stage.setScene(scene);
    stage.show();
  }

  private void setUpMenuBarBox() {
    menuBarBox.setAlignment(Pos.CENTER);
    favoritesRecipesButton = new heartButton(true, 60, 60);
    VBox favoritesBox = new VBox();
    favoritesBox.getChildren().addAll(favoritesRecipesButton.getHeart(), favoritesRecipesButtonLabel);

    Image img = new Image("./images/FridgeLogo.png");
    ImageView view = new ImageView(img);
    view.setFitHeight(50);
    view.setPreserveRatio(true);
    backToFridgeButton.setPrefSize(20, 50);
      //Setting a graphic to the button
    backToFridgeButton.setGraphic(view);
    VBox backToFridgeBox = new VBox();
    backToFridgeBox.getChildren().addAll(backToFridgeButton, backToFridgeButtonLabel);
    VBox saveAndExitBox = new VBox();
    saveAndExitBox.getChildren().addAll(saveAndExitButton, saveAndExitButtonLabel);
    menuBarBox.getChildren().addAll(favoritesBox, backToFridgeBox, saveAndExitBox);
    mainPane.setBottom(menuBarBox);
  }

  private void setRecipesTableColumns() {
    TableColumn nameCol = new TableColumn("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    recipesTable.getColumns().addAll(nameCol);
    addButtonToRecipesTable();
  }

  private void addButtonToRecipesTable() {
      TableColumn<Recipe, Void> colBtn = new TableColumn("");
      colBtn.setStyle( "-fx-alignment: CENTER;");
      Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>> cellFactory = new Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>>() {
          @Override
          public TableCell<Recipe, Void> call(final TableColumn<Recipe, Void> param) {
              final TableCell<Recipe, Void> cell = new TableCell<Recipe, Void>() {
                  private heartButton hb = new heartButton(false, 20, 20);
                  private Button btn = hb.getHeart();
                  {
                      btn.setOnAction((ActionEvent e) -> {
                          clickHeartButtonHandler(hb);
                      });
                  }
                  @Override
                  public void updateItem(Void item, boolean empty) {
                      super.updateItem(item, empty);
                      if (empty) {
                          setGraphic(null);
                      } else {
                          setGraphic(btn);
                      }
                  }
              };
              return cell;
          }
      };
      colBtn.setCellFactory(cellFactory);
      recipesTable.getColumns().add(colBtn);
  }

  private void setButtonHandlers() {
    saveAndExitButton.setOnAction(e -> saveAndExitButtonHandler());
  }

  // Handler Methods
  private void saveAndExitButtonHandler() {
    Platform.exit();
  }

  private void recipeSelectedHandler(Recipe r) throws FileNotFoundException{
    Image image = new Image(new FileInputStream("./images/"+r.getImgFname()));
    recipePic.setImage(image);
    recipeIngredients.setText(r.getIngredients());
    recipeInstructions.setText(r.getInstructions());
  }

  private void clickHeartButtonHandler(heartButton heart) {
    if (heart.getIsFilled()) {
      heart.emptyHeart();
      heart.setIsFilled(false);
    }
    else {
      heart.fillHeart();
      heart.setIsFilled(true);
    }
  }

  private void seedRecipes() {
    Recipe r = new Recipe(1, "Creamy Pesto Shrimp", "Shrimp, Pesto, Cream", "Cook the shrimp", "Hello.com", "creamy_pesto_shrimp.jpeg");
    recipesData.add(r);
  }

  public Scene generateGeneratedRecipesScene() throws FileNotFoundException {
    seedRecipes();
    setUpMenuBarBox();
    mainPane.setTop(title);
    mainPane.setAlignment(title, Pos.CENTER);
    title.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 30;");

    // set up Recipes Table
    recipesTable.setPrefSize(350, 250);
    recipesTable.setItems(recipesData);
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

    //set up Recipe Info Section
    Image image = new Image(new FileInputStream("./images/GenerateRecipes.png"));
    recipePic = new ImageView(image); 
    recipePic.setFitHeight(125);
    recipePic.setFitWidth(125);
    recipeIngredients.setText("Recipe Ingredients Here");
    recipeInstructions.setText("Recipe Instructions Here");
    recipeInfo.getChildren().addAll(recipePic, recipeIngredients, recipeInstructions);
    recipeInfo.setPrefSize(350, 250);
    recipeInfo.setAlignment(Pos.TOP_CENTER);

    center.setPadding(new Insets(10, 0, 10, 0));
    center.getChildren().addAll(recipesTable, recipeInfo);

    mainPane.setCenter(center);

    setButtonHandlers();
    mainPane.setBackground(new Background(new BackgroundFill(Color.web("#FFEEDF"), null, null)));

    return new Scene(mainPane, initWidth, initHeight);
  }

  public static void main(String[] args) {
    launch(args);
  }

  class heartButton {
    private boolean isFilled;
    private int width;
    private int height;
    private Button heart = new Button();
    public heartButton(boolean isFilled, int w, int h) {
      width = w;
      height = h;
      heart.setPrefSize(w, h);
      if (isFilled) {
        fillHeart();
      }
      else {
        emptyHeart();
      }
    }
    private void fillHeart() {
      heart.setStyle("-fx-background-color: red; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
    }

    public void emptyHeart() {
      heart.setStyle("-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
    }
    public boolean getIsFilled() {
      return isFilled;
    }

    public void setIsFilled(boolean status) {
      isFilled = status;
    }

    public Button getHeart() {
      return heart;
    }
  }
}