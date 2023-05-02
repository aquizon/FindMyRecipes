public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String instructions;
    private String link;
    private String imgFname;
<<<<<<< HEAD
    private boolean isFavorited;
=======
    private Boolean favorited; 
>>>>>>> e1180181352183e33509d0fe2b68382d47ee1d49
    
    public Recipe(int id, String name, String ingredients, String instructions, String link, String imgFname, Boolean favorited) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.link = link;
        this.imgFname = imgFname;
<<<<<<< HEAD
        // this.isFavorited = false;
=======
        this.favorited = favorited;
>>>>>>> e1180181352183e33509d0fe2b68382d47ee1d49
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

<<<<<<< HEAD
    // public boolean getIsFavorited() {
    //     return isFavorited;
    // }
=======
    public Boolean getFavorited() {
        return favorited;
    }

    public void favorite() { 
        favorited = !favorited; 
    }
>>>>>>> e1180181352183e33509d0fe2b68382d47ee1d49
  
    @Override
      public String toString() {
          return "Recipe " + id + ": " + name;
      }
  }