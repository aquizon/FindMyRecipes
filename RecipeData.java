import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RecipeData {

  private ArrayList<String> allIngredients = new ArrayList<>(); // no quantities

<<<<<<< HEAD
  // Recipe object has list of ingredients with quantities and list without
  // quantities

=======
>>>>>>> 1e591c02cc96e7eb9e37c33a7708da0bd5b56b04
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
<<<<<<< HEAD
          // addRecipe(row);
          parseRow(row);
=======
          addRecipe(row);
>>>>>>> 1e591c02cc96e7eb9e37c33a7708da0bd5b56b04
        }
      }

    } catch (IOException e) {

    }

    return recipeMap;
  }

<<<<<<< HEAD
  private void parseRow(String row) {
    // System.out.println(row);

    // row contains all attributes of the Recipe (ID, ingredients list, instructions
    // list)
    final StringTokenizer tok = new StringTokenizer(row, "|", false);
=======
  private void addRecipe(String recipeString) {
    final String[] measurements = { "can", "cup", "ounce", "oz", "pound", "tablespoon", "teaspoon", "inch" };

    // get the ID, name, list of ingredients, and list of instructions
    final StringTokenizer tok = new StringTokenizer(recipeString, "|", false);
>>>>>>> 1e591c02cc96e7eb9e37c33a7708da0bd5b56b04
    int ID = Integer.parseInt(tok.nextToken());
    String name = tok.nextToken();
    String ingredientsStr = tok.nextToken();
    String instructionsStr = tok.nextToken();

<<<<<<< HEAD
    ingredientsStr = ingredientsStr.replace("[", "").replace("]", "");
    String[] ingredientsList = ingredientsStr.split("',");

    System.out.println("\n" + name);
    // System.out.println(Arrays.toString(ingredientsList));

    // after creating the list, virtually all of the tokens will start with "\'"
    // iterate through the elements in the list and remove it to get the ingredients
    // with quantities
    ArrayList<String> ingredientsWithQuantities = parseIngredientsWithQuantities(ingredientsList);

    ArrayList<String> ingredientsNoDetails = parseIngredientsNoDetails(ingredientsWithQuantities);

    printArrayList(ingredientsWithQuantities);
    System.out.println();
    printArrayList(ingredientsNoDetails);

    // create the Recipe object

  }

  // get ingredients without quantities and the end details (--xxx) and adjectives
  // (large, small, medium)
  private ArrayList<String> parseIngredientsNoDetails(ArrayList<String> ingredientsWithQuantities) {
    ArrayList<String> ingredientsNoDetails = new ArrayList<>();
    for (String ingredient : ingredientsWithQuantities) {

      // System.out.println(ingredient.getClass().getName());

      String test = new String(ingredient).strip();
      // System.out.println(test);

      String[] testTokens = test.split("--");
      // System.out.println(Arrays.toString(testTokens));

      ingredient = testTokens[0];

      // System.out.println(ingredient);

      // remove the details (quantity, other details, etc.)
      ingredient = removeDetails(ingredient);

      ingredientsNoDetails.add(ingredient);

    }

    return ingredientsNoDetails;
  }

  private String removeDetails(String ingredient) {
    final String[] measurements = { "can", "cup", "ounce", "oz", "pound", "tablespoon", "teaspoon", "inch", "pinch",
        "clove", "slice", "bunch", "quart", "sprig", "cube", "dash", "package", "jar", "bottle" };

    final String[] sizes = { "small", "medium", "large" };

    String ingredientNoMeasurement = ingredient;

    String[] ingredientTokens = ingredient.split(" ");

    String splitToken = null; // to determine where to split the string (either a measurement or a size word
                              // e.g. 1 small onion)

    // NOTE: the measurement word CANNOT be the last token in the string (e.g. bunch
    // of sprigs --> 1/2 bunch fresh cilantro sprigs --> sprig is a measure word but
    // treat "bunch of fresh cilantro sprigs" as the ingredient and "bunch" as the
    // measure
    // word)
    for (int i = 0; i < ingredientTokens.length; i++) {
      for (String measure : measurements) {
        if ((ingredientTokens[i].toUpperCase()).contains(measure.toUpperCase())) {
          if (i != ingredientTokens.length - 1) {
            splitToken = ingredientTokens[i];
            if (splitToken.contains(")")) {
              splitToken = splitToken.replace(")", "");
              ingredientNoMeasurement = ingredientNoMeasurement.replace(")", "");
            }
          }
        }
      }
      for (String size : sizes) {
        if ((ingredientTokens[i].toUpperCase()).contains(size.toUpperCase())) {
          splitToken = size;
        }
      }
      // if the token is a number (try)
      try {
        Integer.parseInt(ingredientTokens[i]);
        splitToken = ingredientTokens[i];
      } catch (NumberFormatException nfe) {
        // do nothing
      }
      // if the token is a fraction
      try {
        // try splitting the token by /
        String[] numeratorAndDenominator = ingredientTokens[i].split("/");
        if (numeratorAndDenominator.length == 2) {
          Integer.parseInt(numeratorAndDenominator[0].trim());
          Integer.parseInt(numeratorAndDenominator[1].trim());

          splitToken = ingredientTokens[i];

        }
      } catch (NumberFormatException nfe) {
        // do nothing
      }
    }

    if (splitToken != null) {
      ingredientNoMeasurement = ingredientNoMeasurement.split(splitToken)[1].strip();
    }

    // replace singular with plural
    if (ingredientNoMeasurement.toUpperCase().equals("tomato".toUpperCase()))
      ingredientNoMeasurement = "tomatoes";
    else if (ingredientNoMeasurement.toUpperCase().equals("potato".toUpperCase()))
      ingredientNoMeasurement = "potatoes";

    // System.out.println("ingredient without measurement: " +
    // ingredientNoMeasurement);

    return ingredientNoMeasurement;
  }

  private void printArrayList(ArrayList<String> arrList) {
    for (String str : arrList) {
      System.out.println(str);
    }
  }

  // returns an ArrayList of one recipe's ingredients with the quantities and
  // extra details included
  private ArrayList<String> parseIngredientsWithQuantities(String[] ingredientsList) {
    ArrayList<String> ingredientsWithQuantities = new ArrayList<>();

    for (String ingredient : ingredientsList) {
      ingredient = ingredient.strip().replace("'", "");
      // System.out.println(ingredient);
      ingredientsWithQuantities.add(ingredient);
    }

    return ingredientsWithQuantities;
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

=======
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
>>>>>>> 1e591c02cc96e7eb9e37c33a7708da0bd5b56b04
  }

}
