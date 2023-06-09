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
import javafx.beans.binding.Bindings;

public class Test extends Application {
    TableView<Recipe> favoritesTable = new TableView<>();
    FavoritesList l1 = new FavoritesList();
    public static void main(String args[]){          
        launch(args);     
    } 
        
    @Override    
    public void start(Stage stage) throws Exception {
        seedRecipes();
        setFavoritesTableColumns();
        favoritesTable.setItems(l1.getFavoritesList());
        getTableViewValues();
        stage.setScene(new Scene(favoritesTable));
        stage.show();
    }

    private void seedRecipes() {
        RecipeData rd = new RecipeData("Recipes_Dataset_Modified.csv");
        Map<String, Recipe> rmap = rd.getRecipeMap();
        int count = 0;
        for (String rname : rmap.keySet()) {
          Recipe r = rmap.get(rname);
          l1.addRecipe(r);
          if (count == 1) {
            r.setIsFavorited(true);
          }
          count++;
          if (count >= 5) {
            break;
          }
        } 
      }

    private void getTableViewValues() {
        // ArrayList<String> values = new ArrayList<>();
        // ObservableList<TableColumn> columns = favoritesTable.getColumns();

        for (Recipe row : favoritesTable.getItems()) {
            System.out.println(row);
        }

        // return values;
    }  

    private void setFavoritesTableColumns() {
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        TableColumn favoritedCol = new TableColumn("Is Favorited");
        favoritedCol.setCellValueFactory(new PropertyValueFactory<>("isFavorited"));
        favoritedCol.setCellFactory(col -> {
            TableCell<Recipe, Boolean> cell = new TableCell<>();
            cell.itemProperty().addListener((obs, old, newVal) -> {
                if (newVal != null) {
                    Node hb = createHeartGraphic(newVal);
                    cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(hb));
                }
            });
            return cell;
        });

        favoritesTable.getColumns().addAll(nameCol, favoritedCol);
        addButtonToRecipesTable();
    }

    private Node createHeartGraphic(Boolean isFavorited){
        heartButton hb;
        if(isFavorited){
            hb = new heartButton(true, 20, 20);
        }
        else {
            hb = new heartButton(false, 20, 20);
        }
        return hb.getHeart();
    }

    private Node createFilledHeartButton(){
        heartButton hb = new heartButton(false, 20, 20);
        return hb.getHeart();
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
                            Recipe selectedRecipe = getTableView().getItems().get(getIndex());
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
}
