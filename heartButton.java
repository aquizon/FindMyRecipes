import javafx.scene.control.Button;

public class heartButton {
    private boolean isFilled;
    private int width;
    private int height;
    private Button heart = new Button();
    public heartButton(boolean isFilled, int w, int h) {
      width = w;
      height = h;
      heart.setPrefSize(w, h);
      this.isFilled = isFilled;
      if (isFilled) {
        fillHeart();
      }
      else {
        emptyHeart();
      }
    }
    public void fillHeart() {
      heart.setStyle("-fx-background-color: red; -fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
    }

    public void emptyHeart() {
      heart.setStyle("-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";");
    }
    public boolean getIsFilled() {
      return isFilled;
    }

    public void setIsFilled(boolean status) {
      isFilled = status;
    }

    public Button getHeart() {
      return heart;
    }
  }