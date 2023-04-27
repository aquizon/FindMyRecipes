import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RecipeData {

  private ArrayList<String> allIngredients = new ArrayList<>(); // no quantities

  // NOTE: so the ingredients being extracted from the CSV are currently stored as
  // String, eventually we will have to use Ingredient objects (which include
  // categories)

  private Map<String, Recipe> recipeMap;

  public RecipeData(String csv) {
    this.recipeMap = parseFile(csv);

  }

  private Map<String, Recipe> parseFile(String csv) {
    Map<String, Recipe> recipeMap = new HashMap<>();

    try { // read and parse csv file
      final BufferedReader br = new BufferedReader(
          new InputStreamReader(RecipeData.class.getClassLoader().getResourceAsStream(csv)));

      br.readLine(); // skip the first row which contains the column names

      while (br.ready()) {
        String row = br.readLine();

        if (row != null) {
          addRecipe(row);
        }
      }

    } catch (IOException e) {

    }

    return recipeMap;
  }

  private void addRecipe(String recipeString) {
    final String[] measurements = { "can", "cup", "ounce", "oz", "pound", "tablespoon", "teaspoon", "inch" };

    // get the ID, name, list of ingredients, and list of instructions
    final StringTokenizer tok = new StringTokenizer(recipeString, "|", false);
    int ID = Integer.parseInt(tok.nextToken());
    String name = tok.nextToken();
    String ingredientsStr = tok.nextToken();
    String instructionsStr = tok.nextToken();

    System.out.println("\n" + name);

    // converting the Strings containing lists into lists
    ingredientsStr = ingredientsStr.replace("[", "").replace("]", "");
    String[] ingredientsList = ingredientsStr.split("',");

    System.out.println("ingredientsList: " + Arrays.toString(ingredientsList));

    ArrayList<String> ingredientsWithQuantities = createIngredientsWithQuantities(ingredientsList);

    // eventually add recipe to the map
  }

  private ArrayList<String> createIngredientsWithQuantities(String[] ingredientsList) {
    ArrayList<String> ingredients = new ArrayList<>();

    for (String ingredient : ingredientsList) {
      String ingredientWithQty = ingredient.strip();
      // System.out.println(str.charAt(0) == '\'');
      ingredientWithQty = ingredientWithQty.replace("'", ""); // have to
      ingredients.add(ingredientWithQty);
      System.out.println("ingredient with quantity: " + ingredientWithQty);
    }

    return ingredients;
  }

  // update allIngredients ArrayList too
  private ArrayList<String> createIngredientsNoQuantities(ArrayList<String> ingredientsWithQuantities) {

    ArrayList<String> ingredients = new ArrayList<>();

    for (String ingredientWithQty : ingredientsWithQuantities) {
      // extract just the ingredient itself (no quantities, brand names, whether it
      // should be diced/chopped/etc.)
      ingredients.add(getIngredientWithoutQuantity(ingredientWithQty));
    }

    return ingredients;
  }

  private String getIngredientWithoutQuantity(String ingredientWithQuantity) {
    String ingredient = null;
    String lastMeasureWord = null;

    // find meausurement words
    String[] measurements = { "can", "cup", "ounce", "oz", "pound", "tablespoon", "teaspoon", "inch" };

    return ingredient;
  }

}
