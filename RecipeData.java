import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RecipeData {

  private ArrayList<String> allIngredients; // no quantities

  // Recipe object has list of ingredients with quantities and list without
  // quantities

  // Recipe object has list of ingredients with quantities and list without
  // quantities

  // NOTE: so the ingredients being extracted from the CSV are currently stored as
  // String, eventually we will have to use Ingredient objects (which include
  // categories)

  private Map<String, Recipe> recipeMap;

  public RecipeData(String csv) {
    this.recipeMap = parseFile(csv);
    this.allIngredients = getAllIngredients(recipeMap);

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
          // addRecipe(row);
          parseRow(row, recipeMap);
        }
      }

    } catch (IOException e) {

    }

    // print out all ingredients

    return recipeMap;
  }

  private void parseRow(String row, Map<String, Recipe> recipeMap) {
    // System.out.println(row);

    // row contains all attributes of the Recipe (ID, ingredients list, instructions
    // list)
    final StringTokenizer tok = new StringTokenizer(row, "|", false);
    int ID = Integer.parseInt(tok.nextToken());
    String name = tok.nextToken();
    String ingredientsStr = tok.nextToken();
    String instructionsStr = tok.nextToken();

    ingredientsStr = ingredientsStr.replace("[", "").replace("]", "");
    String[] ingredientsList = ingredientsStr.split("',");

    // System.out.println("\n" + name);
    // System.out.println(Arrays.toString(ingredientsList));

    // after creating the list, virtually all of the tokens will start with "\'"
    // iterate through the elements in the list and remove it to get the ingredients
    // with quantities
    ArrayList<String> ingredientsWithQuantities = parseIngredientsWithQuantities(ingredientsList);

    ArrayList<String> ingredientsNoDetails = parseIngredientsNoDetails(ingredientsWithQuantities);

    ArrayList<String> instructions = parseInstructions(instructionsStr);

    // printArrayList(ingredientsWithQuantities);
    // System.out.println();
    // printArrayList(ingredientsNoDetails);

    // create the Recipe object
    // attributes: ID, name, ingredients with quantities, ingredietns no quantities,
    // instructions,

    recipeMap.put(name, new Recipe(ID, name, ingredientsWithQuantities, ingredientsNoDetails, instructions, "", false));

  }

  private ArrayList<String> parseInstructions(String instructionsStr) {
    ArrayList<String> instructions = new ArrayList<>();

    instructionsStr = instructionsStr.replace("[", "").replace("]", "");
    String[] instructionsList = instructionsStr.split("',");

    for (String instruction : instructionsList) {
      instruction = instruction.replace("'", "").strip();
      instructions.add(instruction);
    }

    return instructions;
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

      // capitalize ingredient

      ingredientsNoDetails.add(capitalizeIngredient(ingredient));

    }

    return ingredientsNoDetails;
  }

  private String capitalizeIngredient(String ingredient) {
    String capitalizedIngredient = "";

    // split the string
    String[] ingredientWords = ingredient.split(" ");
    for (String word : ingredientWords) {
      if (!word.toLowerCase().equals("of")) {
        String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
        capitalizedIngredient += cap + " ";
      } else {
        capitalizedIngredient += word + " ";
      }
    }

    return capitalizedIngredient.strip();
  }

  // takes a String containing the ingredient (e.g. 3 small tomatoes--diced) and
  // removes the details like quantity, size, the stuff at the end
  private String removeDetails(String ingredient) {
    final String[] measurements = { "can", "cup", "ounce", "oz", "pound", "tablespoon", "teaspoon", "inch", "pinch",
        "clove", "slice", "bunch", "quart", "sprig", "cube", "dash", "package", "jar", "bottle", "packet" };

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
    else if (ingredientNoMeasurement.toUpperCase().equals("green onion".toUpperCase()))
      ingredientNoMeasurement = "green onions";

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

  // helper method that returns the list of all ingredients from all recipes in
  // the dataset
  private ArrayList<String> getAllIngredients(Map<String, Recipe> recipeMap) {
    ArrayList<String> ingredients = new ArrayList<>();
    for (String recipe : recipeMap.keySet()) {
      for (String ingredient : recipeMap.get(recipe).getIngredientNoQuantities()) {
        if (!ingredients.contains(ingredient)) {
          ingredients.add(ingredient);
        }
      }
    }

    return ingredients;

  }

  public Map<String, Recipe> getRecipeMap() {
    return recipeMap;
  }

  public void printIngredients() {
    for (String ingredient : allIngredients) {
      System.out.println(ingredient);
    }
  }
}
