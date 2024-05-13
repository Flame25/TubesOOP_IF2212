package org.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Grass extends SuperObject {

  public Object_Grass() {
    name = "Grass";
    collision = true;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/plants/Grass.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
