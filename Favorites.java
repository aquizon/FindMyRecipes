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

// public class Favorites {
public class Favorites extends Application {
    private Stage stage;
    private Scene scene;

    // set the dimensions of the stage
    private final static int initWidth = 675;
    private final static int initHeight = 500;

    //create the tableview of favorited recipes. 
    static private TableView<Recipe> favoritesTable = new TableView<>();

    // ObservableList objects to be associated with the TableView objects
    private static ObservableList<Recipe> favoritesData = FXCollections.observableArrayList();

    //Topmost label
    static Label title = new Label("FindMyRecipes");

    //Buttons / Labels for the bottom menu bar. 
    static Button generateRecipesButton = new Button();
    static Label generateRecipesButtonLabel = new Label("Generate Recipes");
    static Button backToFridgeButton = new Button();
    static Label backToFridgeButtonLabel = new Label("Back to Fridge");
    static Button saveAndExitButton = new Button("Exit Image Here");
    static Label saveAndExitButtonLabel = new Label("Save and Exit");

    static BorderPane mainPane = new BorderPane(); // main container

    static HBox menuBarBox = new HBox(100); // contains the three buttons at the bottom

    static HBox center = new HBox(10);
    static VBox recipeInfo = new VBox(20);
    static ImageView recipePic;
    static Text recipeIngredients = new Text();
    static Text recipeInstructions = new Text();

    public void start(Stage stage) throws FileNotFoundException {
        Scene scene = generateFavoritesScene();
        stage.setScene(scene);
        stage.show();
    }

    private static Button makeHeartFilledButton(int width, int height) {
        Button heart = new Button();
        heart.setStyle("-fx-background-color: red; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
        heart.setPrefSize(width, height);
        return heart;
    }
    
    private static Button makeHeartEmptyButton(int width, int height) {
        Button heart = new Button();
        heart.setStyle("-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
        heart.setPrefSize(width, height);
        return heart;
    }


    private static void setUpMenuBarBox() {
        menuBarBox.setAlignment(Pos.CENTER);
        //favoritesRecipesButton = makeHeartFilledButton(60, 60);
        // favoritesRecipesButton.setPrefSize(width, height);


        //VBox favoritesBox = new VBox();
        //favoritesBox.getChildren().addAll(favoritesRecipesButton, favoritesRecipesButtonLabel);
        Image imgGen = new Image("./images/GenerateRecipes.png");
        ImageView viewGen = new ImageView(imgGen);
        viewGen.setFitHeight(60);
        viewGen.setPreserveRatio(true);
        generateRecipesButton.setPrefSize(60, 60);
        //Setting a graphic to the button
        generateRecipesButton.setGraphic(viewGen);
        generateRecipesButton.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 65px; " +
            "-fx-min-height: 65px; " +
            "-fx-max-width: 65px; " +
            "-fx-max-height: 65px;"
        );
        VBox generateRecipesBox = new VBox();
        generateRecipesBox.getChildren().addAll(generateRecipesButton, generateRecipesButtonLabel);

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
        menuBarBox.getChildren().addAll(generateRecipesBox, backToFridgeBox, saveAndExitBox);
        mainPane.setBottom(menuBarBox);
    }

    private static void setFavoritesTableColumns() {
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        favoritesTable.getColumns().addAll(nameCol);
        addButtonToFavoritesTable();
      }

    private static void addButtonToFavoritesTable() {
        TableColumn<Recipe, Void> colBtn = new TableColumn("");
        colBtn.setStyle( "-fx-alignment: CENTER;");
        Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>> cellFactory = new Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>>() {
            @Override
            public TableCell<Recipe, Void> call(final TableColumn<Recipe, Void> param) {
                final TableCell<Recipe, Void> cell = new TableCell<Recipe, Void>() {
                    private Button btn = makeHeartFilledButton(20, 20);
                    {
                        btn.setOnAction((ActionEvent e) -> {
                            btn = makeHeartEmptyButton(20, 20); //this needs tweaking. It's not actually adding anything. 
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
        favoritesTable.getColumns().add(colBtn);
    }

    private static void setButtonHandlers() {
        saveAndExitButton.setOnAction(e -> saveAndExitButtonHandler());
    }
    
    // Handler Methods
    private static void saveAndExitButtonHandler() {
    Platform.exit();
    }
    
    private static void recipeSelectedHandler(Recipe r) throws FileNotFoundException{
        Image image = new Image(new FileInputStream(r.getImgFname()));
        recipePic.setImage(image);
        recipeIngredients.setText(r.getIngredients());
        recipeInstructions.setText(r.getInstructions());
    }
    
    private static void seedRecipes() {
        Recipe r = new Recipe(1, "Creamy Pesto Shrimp", "Shrimp, Pesto, Cream", "Cook the shrimp", "Hello.com", "creamy_pesto_shrimp.jpeg");
        favoritesData.add(r);
    }

    public static Scene generateFavoritesScene() throws FileNotFoundException {
        seedRecipes();
        setUpMenuBarBox();
        mainPane.setTop(title);
        mainPane.setAlignment(title, Pos.CENTER);
        title.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 30;");
    
        // set up Recipes Table
        favoritesTable.setPrefSize(350, 250);
        favoritesTable.setItems(favoritesData);
        setFavoritesTableColumns();
    
        favoritesTable.setOnMouseClicked((MouseEvent event) -> {
          if(event.getButton().equals(MouseButton.PRIMARY)){
              Recipe currRecipe = favoritesTable.getSelectionModel().getSelectedItem();
              try {
                recipeSelectedHandler(currRecipe);
              } catch(FileNotFoundException e) {
                e.printStackTrace();
              }
          }
      });
    
        //set up Recipe Info Section
        Image image = new Image(new FileInputStream("./images/GenerateRecipes.png")); // this png needs to be replaced with the heart but I don't have the png for that...
        recipePic = new ImageView(image); 
        recipePic.setFitHeight(125);
        recipePic.setFitWidth(125);
        recipeIngredients.setText("Recipe Ingredients Here");
        recipeInstructions.setText("Recipe Instructions Here");
        recipeInfo.getChildren().addAll(recipePic, recipeIngredients, recipeInstructions);
        recipeInfo.setPrefSize(350, 250);
        recipeInfo.setAlignment(Pos.TOP_CENTER);
    
        center.setPadding(new Insets(10, 0, 10, 0));
        center.getChildren().addAll(favoritesTable, recipeInfo);
    
        mainPane.setCenter(center);
    
        setButtonHandlers();
        mainPane.setBackground(new Background(new BackgroundFill(Color.web("#FFEEDF"), null, null)));
    
        return new Scene(mainPane, initWidth, initHeight);
      }
    
      public static void main(String[] args) {
        launch(args);
      }
} 