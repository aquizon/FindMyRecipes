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

    public void removeRecipe(Recipe r) {
        favoritesList.remove(r);
    }

    public void setFlist(ObservableList<Recipe> favorites) {
        System.out.println("favorites on flist: " + favorites);
        favoritesList = favorites;
    }

    public void printFavoritesList() {
        for (Recipe recipe : favoritesList) {
            System.out.print(recipe.getName() + ", ");
        }
    }

}
