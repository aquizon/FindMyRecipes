import java.io.*;
import java.util.*;
import javafx.collections.ObservableList;

public class FindMyRecipesFileHandler {

  // NOTE: since the save and exit button is on multiple scenes, do a call to a
  // call to this function?
  // need to save the recipe/ingredient filenames???
  // save String ingredients from fridgeDataNames
  public static void saveRecords(ObservableList<Recipe> favoriteRecipes, ArrayList<String> ingredientsAdded) {

    try {
      FileOutputStream userFile = new FileOutputStream("user.dat");
      DataOutputStream userWriter = new DataOutputStream(userFile);

      // writing filenames used (when loading, check if the filenames used are the
      // same)
      // writing all the fridge ingredients to file
      // write number of ingredients (to be used for reading)
      userWriter.writeInt(ingredientsAdded.size());
      for (String ingredient : ingredientsAdded) {
        userWriter.writeUTF(ingredient);
      }
      userWriter.flush();
      userWriter.close();
    } catch (IOException ioe) {
      System.out.println("Error writing file");
    }
  }

  public static void readRecords(ObservableList<Recipe> favoriteRecipes, ObservableList<String> fridgeDataNames,
      String recipeFname, String ingredientFname) {

    try {
      // see if the filenames used are the same
      // structure:
      // recipeFname
      // ingredientFname
      // num ingredients in fridge
      // all the ingredients in fridge

      FileInputStream userFile = new FileInputStream("user.dat");
      DataInputStream userReader = new DataInputStream(userFile);

      String savedRecipeFname = userReader.readUTF();
      String savedIngredientFname = userReader.readUTF();

      // if the ingredient filenames match, populate the user's fridge
      if (ingredientFname.equals(savedIngredientFname)) {
        int totalFridgeIngredients = userReader.readInt();

        // note: need to get the ingredient name, find the corresponding Ingredient
        // object in the ingredientData, and add the Ingredient object to theu user's
        // ObservableList fridge
        for (int i = 0; i < totalFridgeIngredients; i++) {
          String ingredientName = userReader.readUTF();
          fridgeDataNames.add(ingredientName);

          // get the Ingredient object and add to ObservableList<Ingredient> -- just
          // iterate through IngredientsData somewhere else
        }
      }

    } catch (IOException ioe) {
      System.out.println("Error reading file");
    }
  }
}
