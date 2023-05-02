public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String instructions;
    private String link;
    private String imgFname;
    private Boolean favorited; 
    
    public Recipe(int id, String name, String ingredients, String instructions, String link, String imgFname, Boolean favorited) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.link = link;
        this.imgFname = imgFname;
        this.favorited = favorited;
    }
  
    public int getId() {
        return id;
    }
  
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getLink() {
        return link;
    }

    public String getImgFname() {
        return imgFname;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void favorite() { 
        favorited = !favorited; 
    }
  
    @Override
      public String toString() {
          return "Recipe " + id + ": " + name;
      }
  }