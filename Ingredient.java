public class Ingredient {

  private int id;
  private String name;
  private String category;
  private String imgFname;
  
  public Ingredient(int id, String name, String category) {
    this(id, name, category, "");
  }

  public Ingredient(int id, String name, String category, String imgFname) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.imgFname = imgFname;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }
  
  public String getImgFname() { 
    return imgFname;
  }

  @Override
	public String toString() {
		return "Ingredient " + id + ": " + name;
	}
}