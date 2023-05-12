import java.io.*;
import java.util.*;
import javafx.collections.ObservableList;

public class FindMyRecipesFileHandler {

  public static void saveFridge(ArrayList<String> fridgeDataNames) {
    try {
      FileOutputStream fridgeFile = new FileOutputStream("fridge.dat");
      DataOutputStream fridgeWriter = new DataOutputStream(fridgeFile);

      // writing filenames used (when loading, check if the filenames used are the
      // same)
      // writing all the fridge ingredients to file
      // write number of ingredients (to be used for reading)
      if (fridgeDataNames != null) {
        fridgeWriter.writeInt(fridgeDataNames.size());
        for (String ingredient : fridgeDataNames) {
          fridgeWriter.writeUTF(ingredient);
        }

      }
      fridgeWriter.flush();
      fridgeWriter.close();
    } catch (IOException ioe) {
      System.out.println("Error writing file");
    }
  }

  public static void saveFavorites(ObservableList<Recipe> favorites) {
    try {
      System.out.println("Favorites on handler");
      FileOutputStream favoritesFile = new FileOutputStream("favorites.dat");
      DataOutputStream favoritesWriter = new DataOutputStream(favoritesFile);

      // writing filenames used (when loading, check if the filenames used are the
      // same)
      // writing all the fridge ingredients to file
      // write number of ingredients (to be used for reading)
      ArrayList<String> recipeNames = new ArrayList<>();
      if (favorites != null) {
        favoritesWriter.writeInt(favorites.size());
        for (Recipe recipe : favorites) {
          favoritesWriter.writeUTF(recipe.getName());
          recipeNames.add(recipe.getName());
        }
      }
      System.out.println(recipeNames);
      favoritesWriter.flush();
      favoritesWriter.close();
    } catch (IOException ioe) {
      System.out.println("Error writing file");
    }
  }

  public static ArrayList<String> readFridgeData() {
    ArrayList<String> fridgeDataNames = new ArrayList<>();

    try {
      // see if the filenames used are the same
      // structure:
      // recipeFname
      // ingredientFname
      // num ingredients in fridge
      // all the ingredients in fridge

      FileInputStream userFile = new FileInputStream("fridge.dat");
      DataInputStream userReader = new DataInputStream(userFile);

      // if the ingredient filenames match, populate the user's fridge
      try {
        // use try in case the .dat is empty (user first opens application)
        int totalFridgeIngredients = userReader.readInt();

        // note: need to get the ingredient name, find the corresponding Ingredient
        // object in the ingredientData, and add the Ingredient object to theu user's
        // ObservableList fridge
        for (int i = 0; i < totalFridgeIngredients; i++) {
          String ingredientName = userReader.readUTF();
          fridgeDataNames.add(ingredientName);
        }

        userReader.close();
        return fridgeDataNames;
      } catch (Exception e) {

      }

    } catch (IOException ioe) {
      System.out.println("Error reading file: reading fridge");
    }

    return fridgeDataNames;
  }

  public static ArrayList<String> readFavoriteNames() {
    ArrayList<String> favoriteNames = new ArrayList<>();

    try {
      FileInputStream userFile = new FileInputStream("favorites.dat");
      DataInputStream userReader = new DataInputStream(userFile);

      try {
        int totalFavorites = userReader.readInt();

        for (int i = 0; i < totalFavorites; i++) {
          String recipeName = userReader.readUTF();
          favoriteNames.add(recipeName);
        }

        userReader.close();

        return favoriteNames;

      } catch (Exception e) {
        // do nothing
      }

    } catch (IOException ioe) {
      System.out.println("Error reading file");
    }

    return favoriteNames;

  }

}
