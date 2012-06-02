package hotpixfix;

/**
 * This class implements an int Dimension
 */
class Dimension {

  /** width of this */
  private int width;

  /** height of this */
  private int height;

  /** size of this */
  private int size;

  /**
   * Constructs a new Dimension 
   * out of th given width, height and size
   * @param width the given width
   * @param height the given height
   * @param size the given size
   */
  public Dimension(int width, int height, int size) {
    this.width = width;
    this.height = height;
    this.size = size;
  }

  /**
   * Returns the width of this
   * @return the width of this
   */
  public int getWidth() { return width; }

  /**
   * Returns the height of this
   * @return the height of this
   */
  public int getHeight() { return height; }

  /**
   * Returns the size of this
   * @return the size of this
   */
  public int getSize() { return size; }

}
