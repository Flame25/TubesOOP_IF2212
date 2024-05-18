package org.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadImage {
  public static BufferedImage GetSpriteAtlas(String fileName) {
    BufferedImage img = null;
    InputStream is = LoadImage.class.getResourceAsStream("/" + fileName);
    try {
      img = ImageIO.read(is);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return img;
  }
}
