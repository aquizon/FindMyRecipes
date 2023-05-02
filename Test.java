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

public class Test extends Application {
    TableView<Recipe> favoritesTable = new TableView<>();
    public static void main(String args[]){          
        launch(args);     
    } 
        
    @Override    
    public void start(Stage stage) throws Exception {
        FavoritesList l1 = new FavoritesList();
        Recipe r = new Recipe(1, "Creamy Pesto Shrimp", "Shrimp, Pesto, Cream", "Cook the shrimp", "Hello.com", "creamy_pesto_shrimp.jpeg");
        l1.addRecipe(r);
        setFavoritesTableColumns();
        favoritesTable.setItems(l1.getFavoritesList());
        stage.setScene(new Scene(favoritesTable));
        stage.show();
    }

    private void setFavoritesTableColumns() {
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        favoritesTable.getColumns().addAll(nameCol);
    }
}
