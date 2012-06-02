package hotpixfix;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
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

  /** holds the size of the hot pixel */
  private int size;

  /** holds pthe position of the Pixels */
  private LinkedList<Dimension> pixels;

  /**
   * Constructs a New PixelFix Image 
   * in which the given pixels ar repleaced
   * @param path the given image location
   * @param pixels the given pixel positions
   * @param size the size of the hotpixel
   */
  public PixelFix(File path, LinkedList<Dimension> pixels, int size) {
    if (path == null || pixels == null) {
      throw new IllegalArgumentException();
    }

    this.path = path;
    this.size = size;

    try {
      this.img = ImageIO.read(path);
    } catch (IOException e) {
      System.out.println("Couldn't read file " + path.getPath() + ".");
      e.printStackTrace();
    }
    
    for (Dimension d : pixels) {
      repleacePixel(d, calcAvg(d));
    }

    save();
  }

  private void repleacePixel(Dimension d, int rgb) {
    int sw = d.getWidth() - size;
    int ew = d.getWidth() + size;
    int sh = d.getHeight() - size;
    int eh = d.getHeight() + size;

    if (sw < 0) sw = 0;
    if (ew > img.getWidth()) ew = img.getWidth();
    if (sh < 0) sh = 0;
    if (eh > img.getHeight()) eh = img.getHeight();

    for (int w = sw; w < ew; w++) {
      for (int h = sh; h < eh; h++) {
        img.setRGB(w, w, rgb);
      }
    }

  }

  private void calcSurroundingPositions(Dimension d) {
    LinkedList<Dimension> pos = new LinkedList<Dimension>();

    int sw = d.getWidth() - 2 * size;
    int ew = d.getWidth() + 2 * size;
    int sh = d.getHeight() - 2 * size;
    int eh = d.getHeight() + 2 * size;

    if (sw < 0) sw = 0;
    if (ew > img.getWidth()) ew = img.getWidth();
    if (sh < 0) sh = 0;
    if (eh > img.getHeight()) eh = img.getHeight();

    for (int w = sw; w < ew; w++) {
      if (w > d.getWidth() - size && w < d.getWidth() + size) continue;

      for (int h = sh; h < eh; h++) {
        if (h > d.getHeight() - size && h < d.getHeight() + size) continue;
        
        pos.add(new Dimension(w, h));
      }
    }
  }

  private int calcAvg(Dimension d) {
    int rgb[] = new int[3];
    int i = 0;
    
    for (Dimension p : calcSurroundingPositions(d)) {
      if (p.getWidth() < 0 || p.getWidth() >= img.getWidth()
          || p.getHeight() < 0 || p.getHeight() >= img.getHeight()) {
          continue;
      }
      Color c = new Color(img.getRGB(p.getWidth(), p.getHeight()));
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

  private void save() {
    try {
      ImageIO.write(img, "jpg", path);
    } catch (IOException e) {
      System.out.println("Couldn't write file " + path.getPath() + ".");
      e.printStackTrace();
    }
  }

}
