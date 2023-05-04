import java.util.*;

public class Recipe {

    private int id;
    private String name;
    private ArrayList<String> ingredientsWithQuantities; // change to ArrayList - ingredients with quantities (to be
                                                         // displayed in theGenerated Recipes/Favorites window)
    private ArrayList<String> ingredientsNoQuantities; // added ArrayList - ingredients without quantities (to be used
                                                       // in the
    // generated recipes algorithm)
    private ArrayList<String> instructions; // change to ArrayList
    // private String link; // remove -> don't use anymore
    private String imgFname; // use empty strings for now
    private boolean isFavorited;

    public Recipe(int id, String name, ArrayList<String> ingredientsWithQuantities,
            ArrayList<String> ingredientsNoQuantities, ArrayList<String> instructions,
            String imgFname,
            Boolean favorited) {
        this.id = id;
        this.name = name;
        this.ingredientsWithQuantities = ingredientsWithQuantities;
        this.ingredientsNoQuantities = ingredientsNoQuantities;
        this.instructions = instructions;
        // this.link = link;
        this.imgFname = imgFname;
        this.isFavorited = false;
    }

    public void setIsFavorited(boolean newVal) {
        isFavorited = newVal;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredientWithQuantities() {
        return ingredientsWithQuantities;
    }

    public ArrayList<String> getIngredientNoQuantities() {
        return ingredientsNoQuantities;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    // public String getLink() {
    // return link;
    // }

    public String getImgFname() {
        return imgFname;
    }

    public boolean getIsFavorited() {
        return isFavorited;
    }

    @Override
    public String toString() {
        return "Recipe " + id + ": " + name + " is Favorited: " + isFavorited;
    }
}