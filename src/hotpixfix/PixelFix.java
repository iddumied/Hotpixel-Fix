package hotpixfix

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This Class implements an method tho repleace Pixels at a given position
 * with the average pixel of the sorounded pixels
 */
class PixelFix {

  /** holds the image */
  private BufferedImage img;

  /** holds the File name */
  private File path;

  /** holds pthe position of the Pixels */
  private LinkedList<Dimension> pixels;

  /**
   * Constructs a New PixelFix Image 
   * in which the given pixels ar repleaced
   * @param path the given image location
   * @param pixels the given pixel positions
   */
  public PixelFix(File path, LinkedList<Dimension> pixels) {
    if (path == null || pixels == null) {
      throw new IllegalArgumentException();
    }

    this.path = path;

    try {
      this.img = ImageIO.read(path);
    } catch (IOException e) {
      System.out.println("Couldn't read file " + config.getSource().getPath() + ".");
      e.printStackTrace();
    }
    
    for (Dimension d : pixels) {
      img.setRGB(d.getWidth(), d.getHeight(), calcAvg(d));
    }

    save()
  }

  private int calcAvg(Dimension d) {
    Dimension[] positions = new Dimension[8];
    positions[0] = new Dimension(d.getWidth() - 1, d.getHeight() - 1);
    positions[1] = new Dimension(d.getWidth()    , d.getHeight() - 1);
    positions[2] = new Dimension(d.getWidth() + 1, d.getHeight() - 1);
    positions[3] = new Dimension(d.getWidth() - 1, d.getHeight());
    positions[4] = new Dimension(d.getWidth() + 1, d.getHeight());
    positions[5] = new Dimension(d.getWidth() - 1, d.getHeight() + 1);
    positions[6] = new Dimension(d.getWidth()    , d.getHeight() + 1);
    positions[7] = new Dimension(d.getWidth() + 1, d.getHeight() + 1);

    int rgb[] = new int[3];
    int i = 0;
    
    for (Dimension p : positions) {
      if (p.getWidth() < 0 || p.getWidth() >= img.getWidth()
          || p.getHeight() < 0 || p.getHeight() >= img.getHeight()) {
          continue;
      }
      Color c = new Color(img.getRGB(p.getWidth(). p.getHeight()));
      rgb[0] += c.getRed();
      rgb[1] += c.getGreen();
      rgb[2] += c.getBlue();
      i++;
    }

    rgb[0] /= i;
    rgb[1] /= i;
    rgb[2] /= i;
    Color c = new Color(rgb[0], rgb[1], rgb[2]);

    return c.getRGB();
  }

  private save() {
    ImageIO.write(img, "jpg", path);
  }

}
