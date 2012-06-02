package hotpixfix

import java.awt.image.BufferedImage;
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

  /** holds pthe position of the Pixels */
  private LinkedList<Dimension> pixels;

  /**
   * Constructs a New PixelFix Image 
   * in which the given pixels ar repleaced
   * @param path the given image location
   * @param pixels the given pixel positions
   */
  public PixelFix(String path, LinkedList<Dimension> pixels) {


  }

}
