import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class FavoritesList {
    public ObservableList<Recipe> favoritesList;

    public FavoritesList() {
        favoritesList = FXCollections.observableArrayList();
    }

    public ObservableList<Recipe> getFavoritesList() {
        return favoritesList;
    }

    public void addRecipe(Recipe r) {
        favoritesList.add(r);
    }

}

