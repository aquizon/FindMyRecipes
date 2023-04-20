

public class Ingredient {

  private int id;
  private String name;
  private String category;
  
  public Ingredient(int id, String name, String category) {
    this.id = id;
    this.name = name;
    this.category = category;
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

  @Override
	public String toString() {
		return "Ingredient " + id + ": " + name;
	}
}