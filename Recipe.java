public class Recipe {

    private int id;
    private String name;

    // other attributes: ingredients (with quantities), ingredients (without quantities), instructions
    
    public Recipe(int id, String name) {
      this.id = id;
      this.name = name;
    }
  
    public int getId() {
      return id;
    }
  
    public String getName() {
      return name;
    }
  
    @Override
      public String toString() {
          return "Recipe " + id + ": " + name;
      }
  }