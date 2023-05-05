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
import java.lang.instrument.IllegalClassFormatException;

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

import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI.InternalFramePropertyChangeListener;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text; 
import javafx.scene.input.MouseButton;

public class Favorites {
// public class Favorites extends Application {
    private Stage stage;
    private Scene scene;

    // set the dimensions of the stage
    private final int initWidth = 675;
    private final int initHeight = 500;

    //create the tableview of favorited recipes. 
    private static TableView<Recipe> favoritesTable = new TableView<>();

    // ObservableList objects to be associated with the TableView objects
    // public static ObservableList<Recipe> favoritesData = FXCollections.observableArrayList();

    //Topmost label
    Label title = new Label("FindMyRecipes");

    //Buttons / Labels for the bottom menu bar. 
    Button generateRecipesButton = new Button();
    Label generateRecipesButtonLabel = new Label("Generate Recipes");
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

    FavoritesList fList;

    // public void start(Stage stage) throws FileNotFoundException {
    //     Scene scene = generateFavoritesScene();
    //     stage.setScene(scene);
    //     stage.show();
    // }

    private Button makeHeartFilledButton(int width, int height) {
        Button heart = new Button();
        heart.setStyle("-fx-background-color: red; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
        heart.setPrefSize(width, height);
        return heart;
    }
    
    private Button makeHeartEmptyButton(int width, int height) {
        Button heart = new Button();
        heart.setStyle("-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
        heart.setPrefSize(width, height);
        return heart;
    }


    private void setUpMenuBarBox() {
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
        view.setFitWidth(30);
        // view.setPreserveRatio(false);
        backToFridgeButton.setMaxSize(25, 50);
        backToFridgeButton.setMinSize(10, 5);
        //Setting a graphic to the button
        backToFridgeButton.setGraphic(view);
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
        saveAndExitBox.getChildren().addAll(saveAndExitButton, saveAndExitButtonLabel);
        menuBarBox.getChildren().addAll(generateRecipesBox, backToFridgeBox, saveAndExitBox);
        mainPane.setBottom(menuBarBox);
    }

    private void setFavoritesTableColumns() {
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        favoritesTable.getColumns().addAll(nameCol);
        addButtonToFavoritesTable();
    }

    private void addButtonToFavoritesTable() {
        TableColumn<Recipe, Void> colBtn = new TableColumn("");
        colBtn.setStyle( "-fx-alignment: CENTER;");
        Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>> cellFactory = new Callback<TableColumn<Recipe, Void>, TableCell<Recipe, Void>>() {
            @Override
            public TableCell<Recipe, Void> call(final TableColumn<Recipe, Void> param) {
                final TableCell<Recipe, Void> cell = new TableCell<Recipe, Void>() {
                    private heartButton hb = new heartButton(true, 20, 20);
                    private Button btn = hb.getHeart();
                    {
                        btn.setOnAction((ActionEvent e) -> {
                            Recipe selectedRecipe = getTableView().getItems().get(getIndex());
                            clickHeartButtonHandler(hb, selectedRecipe);
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

    private void setButtonHandlers() {
        saveAndExitButton.setOnAction(e -> saveAndExitButtonHandler());
    }
    
    // Handler Methods
    private void saveAndExitButtonHandler() {
    Platform.exit();
    }

    private void clickHeartButtonHandler(heartButton heart, Recipe selectedRecipe) {
        if (heart.getIsFilled()) {
          heart.emptyHeart();
          heart.setIsFilled(false);

          fList.removeRecipe(selectedRecipe);
          selectedRecipe.setIsFavorited(false);
        }
        else {
          heart.fillHeart();
          heart.setIsFilled(true);
          selectedRecipe.setIsFavorited(true);
        }
      }
    
    private void recipeSelectedHandler(Recipe r) throws FileNotFoundException{
        //GridPane recipePane = new GridPane(); 
        //this is commented out for now since the images do not yet exist. 
        Image img = new Image(r.getImgFname());
        recipePic.setFitHeight(100);
        recipePic.setFitWidth(100);
        recipePic.setImage(img);
        
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
        //so this works its just that it is kinda compressed do we want to just make the window bigger overall? 

        //Image image = new Image(new FileInputStream("./images/"+r.getImgFname()));
        //recipePic.setImage(image);
        // recipeIngredients.setText(r.getIngredients());
        // recipeInstructions.setText(r.getInstructions());
    }

    // private void seedRecipes() {
        // Recipe r = new Recipe(1, "Creamy Pesto Shrimp", "Shrimp, Pesto, Cream", "Cook the shrimp", "Hello.com", "creamy_pesto_shrimp.jpeg");
        // fList.addRecipe(r);
    // }

    public Scene generateFavoritesScene(FavoritesList f) throws FileNotFoundException {
        this.fList = f;
        setUpMenuBarBox();
        mainPane.setTop(title);
        mainPane.setAlignment(title, Pos.CENTER);
        title.setStyle("-fx-font: Courier New;"+"-fx-font-weight: bold;"+"-fx-font-size: 30;");
    
        // set up Recipes Table
        favoritesTable.setPrefSize(350, 250);
        favoritesTable.setItems(fList.getFavoritesList());
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
    
      // public static void main(String[] args) {
      //   launch(args);
      // }
} 
