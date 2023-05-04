import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 

public class Ingredient {

  private int id;
  private String name;
  private String category;
  private ImageView img; //I needed to create an image here instead since the delete function is tied to the ingredient itself. 
  
  public Ingredient(int id, String name, String category) {
    this(id, name, category, "");
  }

  public Ingredient(int id, String name, String category, String imgFname) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.img = new ImageView();
    img.setFitHeight(30);
    img.setFitWidth(30);
    img.setImage(new Image(imgFname));
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

  public ImageView getImg() { 
    return img;
  }

  @Override
	public String toString() {
		return "Ingredient " + id + ": " + name;
	}
}