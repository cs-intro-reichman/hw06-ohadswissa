// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		//imageOut = flippedHorizontally(tinypic);
		//System.out.println();
		//print(imageOut);
		//Tests the vertical flipping of an image:
		//imageOut = flippedVertically(tinypic);
		//System.out.println();
		//print(imageOut);
		//luminance test
		//Color check = new Color (255, 0,255);
		//Color grey =luminance(check);
		//System.out.println(grey.getRed()+","+grey.getGreen()+","+grey.getBlue());
		//greyscaled test
		//imageOut = scaled(tinypic,3,5);
		//print(imageOut);
		//blend test
		//Color check1 = new Color (100, 40,100);
		//Color check2 = new Color (200,20,40);
		//Color mix = blend(check1,check2,0.25);
		//System.out.println(mix.getRed()+","+mix.getGreen()+","+mix.getBlue());

	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
        	for (int i = 0; i < numRows; i++) //fills the 2D array
        	{
        		 for (int j = 0; j < numCols; j++) // moves past the columns
        		   {
        			 //takes the information and stores it as a color type
        			 int red = in.readInt();
        			 int green = in.readInt();
        			 int blue = in.readInt();
        			 image[i][j] = new Color(red, green, blue);
        		    }
        	}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
	    int rows = image.length;
        int columns = image[0].length;
	    for (int i = 0; i < rows; i++)
		     {
		       	for (int j = 0; j < columns; j++)
		         {
		         	print(image[i][j]);
		         }
		         System.out.println();
		     }
	}	
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int r = image.length;
		int c = image[0].length;
		int domain = c - 1;//parameter for flipping the array slot with
		Color[][] horizonal = new Color[r][c];//new color 2D array
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c ; j++)
			{
				horizonal[i][j] = image[i][domain-j];
			}
		}
		return horizonal;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int r = image.length;
		int c = image[0].length;
		int domain = r - 1;//parameter for flipping the array slot with
		Color[][] vertical = new Color[r][c];//new color 2D array
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				vertical[i][j] = image[domain-i][j];
			}
		}
		return vertical;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		double nred = 0.299 * pixel.getRed();
		double ngreen = 0.587 * pixel.getGreen();
		double nblue = 0.114 * pixel.getBlue();
		int lum = (int)(nred + ngreen + nblue);
		Color grey = new Color(lum,lum,lum);
		return grey;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int r = image.length;
		int c = image[0].length;
		Color[][] grey = new Color[r][c];//new color 2D array
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				grey[i][j] = luminance(image[i][j]);
			}
		}
		return grey;
	}
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] scale = new Color[height][width];//new color 2D array
		int sourceH = image.length;
		int sourceW = image[0].length;
		int sourceI = 0;
		int sourceJ = 0;
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
			    sourceI = (int) (i * (sourceH / (double) height));
                sourceJ = (int) (j * (sourceW/ (double) width));
                sourceI = Math.min(sourceI, sourceH - 1);
                sourceJ = Math.min(sourceJ, sourceW - 1);
                scale[i][j] = image[sourceI][sourceJ];				
			}
		}
		return scale;
	}
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int nred = (int)(alpha * (c1.getRed()) + (1 - alpha) * (c2.getRed()));
		int ngreen = (int)(alpha * (c1.getGreen()) + (1 - alpha) * (c2.getGreen()));
		int nblue = (int)(alpha * (c1.getBlue()) + (1 - alpha) * (c2.getBlue()));
		Color mix = new Color(nred,ngreen,nblue);
		return mix;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int r = image1.length;
		int c = image1[0].length;
		Color[][] mix = new Color[r][c];//new color 2D array
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
			    mix [i][j] = blend(image1[i][j],image2[i][j],alpha);
			}
		}
		return mix;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int r1 = source.length;
		int c1 = source[0].length;
		int r2 = target.length;
		int c2 = target[0].length;
		int count = n;
		double alpha = 0;
		Color [][] dimension = scaled(target,c1,r1);//makes sure the arrays are on the same size
		Color [][] mix;
		for ( int i = 0; i <= n; i++)
		{
			alpha = (n - i)/n;
			mix = blend(source,dimension,alpha);
			display(mix);
			StdDraw.pause(500); 

		}
	}

	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

