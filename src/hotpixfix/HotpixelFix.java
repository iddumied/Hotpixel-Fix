package hotpixfix

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * implementation of the PixelFix for my Kamera which
 * always has the same bad pixels
 */
class HotpixelFix {
  
  public static void main(String[] args) {
    File path = new File(args[0]);
    LinkedList<Dimension> pixles = new LinkedList<Dimension>();
    pixles.add(new Dimension(1,1));

    new PixelFix(path, pixles);
  }

}
