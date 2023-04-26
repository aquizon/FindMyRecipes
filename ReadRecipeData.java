import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ReadRecipeData {

  // key can either be the recipe ID or the name of the recipe
  static Map<String, Recipe> recipes = new HashMap<>();

  public static void main(String[] args) throws Exception {

    final BufferedReader br = new BufferedReader(new InputStreamReader(ReadRecipeData.class.getClassLoader().getResourceAsStream("RecipeSubset.csv")));

    br.readLine(); // skip the first row which contains the column names

    while (br.ready()) {
      String row = br.readLine();
      // check if the row is not null
      if (row != null) {
        addRecipe(row);
      }
    }
  }

  private static void addRecipe(String recipeStr) {
    // get the ID, name, list of ingredients, and list of instructions
        final StringTokenizer tok = new StringTokenizer(recipeStr, "|", false);
        int ID = Integer.parseInt(tok.nextToken());
        String name = tok.nextToken();
        String ingredientsStr = tok.nextToken();
        String instructionsStr = tok.nextToken();
        
        System.out.println(ID);
        System.out.println(name);
        // System.out.println(ingredientsStr);
        // System.out.println(instructionsStr);

        // converting the Strings containing lists into lists
        ingredientsStr = ingredientsStr.replace("[", "").replace("]", "");
        String[] split = ingredientsStr.split("',");

        // System.out.println("ingredientsStr after split");

        ArrayList<String> ingredientsWithQuantities = new ArrayList<>();
        ArrayList<String> ingredientsNoQuantities = new ArrayList<>();


        // add to ingredientsWithQuantities and ingredientsNoQuantities
        for (String str: split) {
          str = str.strip();
          // System.out.println(str.charAt(0) == '\'');
          str = str.replace("'", ""); // have to 
          ingredientsWithQuantities.add(str);
          // System.out.println(str);

          // get the ingredient without quantities
          
        }

        for (String ingredient: ingredientsWithQuantities) {
          System.out.println(ingredient + "\n");
        }
  }

}