import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 

public class Ingredient {

  private int id;
  private String name;
  private String category;
  private ImageView img; 
  private ImageView imgFridge; //this is one of the wackiest lines of code ive ever written like what why
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
    this.imgFridge = new ImageView(); 
    imgFridge.setFitHeight(30);
    imgFridge.setFitWidth(30);
    imgFridge.setImage(new Image(imgFname));
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

  public ImageView getImgFridge() { 
    return imgFridge;
  }

  @Override
	public String toString() {
		return "Ingredient " + id + ": " + name;
	}
}