import java.awt.Color;
import java.util.ArrayList;

public class Steganography {
    
    ////////////////////////////////////////////////////////////////////
    //
    // ACTIVITY 1: Exploring Color
    //

    //////////////////////////////////////////////////
    // Changing Colors (Exercise 1.6-1.8)

    // 
	/**
	 * Clears the lower (rightmost) two bits of of the Red, Green, and Blue
	 * values of p.
	 * @param p The pixel to clear 
	 */
    public static void clearLow(Pixel p) {
        // extract the red, green, and blue values from the pixel
        int red = p.getRed();
        int green = p.getGreen();
        int blue = p.getBlue();
        // TODO (1.6): clear the lowest two bits of each color using maths and set it to new values!
        red = red/4;
        green = green/4;
        blue = blue/4;
        
        red = red*4;
        green = green*4;
        blue = blue*4;
        
     // TODO (1.7): set the RGB components of the pixel to the new values
        p.setRed(red);
        p.setGreen(green);
        p.setBlue(blue);
    }

     /**
     * Creates a copy of original and then clears the lower two bits of every
     * pixel in the picture. Must use the clearLow method. Returns the Picture copy.
     * @param original the original picture. 
     * @return a copy of the original picture that has the lower two bits
     *  of every pixel cleared
     */
    public static Picture testClearLow(Picture original) {
        // create a new picture as a copy of the original
        Picture copy = new Picture(original);
        // get an array of pixels from the copy
        Pixel[] pixels = copy.getPixels();

        // TODO (1.8): use a for each loop to clear the low bits of each pixel
      for(Pixel i: pixels) {
    	  clearLow(i);
      }



        // return the copy
        return copy;
    }

    //////////////////////////////////////////////////
    // Setting Bits (Exercise 1.10-1.12)

    /**
     * Sets the lower (rightmost) two bits of each red, green, blue value
     * of a pixel to the higher (leftmost) two bits of each red, green, blue value
     * in the specified color.
     * @param p The pixel to modify.
     * @param c The color to use to modify the pixel.
     */
    public static void setLow(Pixel p, Color c) {
        // clear the lowest 2 bits of the pixel
        clearLow(p);
        // extract the red, green, and blue values from the color
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int tempR = p.getRed();
        int tempG = p.getGreen();
        int tempB = p.getBlue();
        // TODO (1.10): get the highest two bits of the color using maths!
        
        red = red/64;
        green = green/64;
        blue = blue/64;
        // TODO (1.11): set the RGB components of the pixel to new values
       
        tempR += red;
        tempG += green;
        tempB += blue;
        p.setRed(tempR);
        p.setGreen(tempG);
        p.setBlue(tempB);

    }

    /**
     * Creates a copy of the specified picture, clears the lower two bits of every pixel
     * in copy, then sets the lower two bits to the higher two bits of the specified color.
     * Must use the setLow method. Returns copy.
     * @param original The original picture to copy
     * @param c The color used to modify the picture copy
     * @return The Picture copy.
     */
    public static Picture testSetLow(Picture original, Color c) {
        // create a new picture as a copy of the original
        Picture copy = new Picture(original);
        // get an array of pixels from the copy
        Pixel[] pixels = copy.getPixels();
        // TODO (1.12): use a for each loop to set the low bits of each pixel
        
        for(Pixel i: pixels) {
        	setLow(i, c);
        }


        // return the copy
        return copy;
    }

    //////////////////////////////////////////////////
    // Revealing Bits (Exercise 1.13-1.14)

    /**
     * Given a picture that has a hidden picture, it reveals the hidden picture.
     * Creates a copy of a picture, sets the highest two bits of each pixel
     * in the copy to the lowest two bits of each pixel in the source.
     * @param The picture that contains a hidden picture.
     * @return The hidden picture.
     */
    public static Picture revealPicture(Picture hidden) {
    	int red;
    	int green;
    	int blue;
    	int lowR;
    	int lowB;
    	int lowG;
        // create a copy of the hidden picture
        Picture copy = new Picture(hidden);
        // get a 2D array of pixels from the copy
        Pixel[][] pixels = copy.getPixels2D();
        // get a 2D array of pixels from the source image
        Pixel[][] source = hidden.getPixels2D();
        
        
        // TODO (1.13): iterate over the pixels using a nested for loop
        
        for(int row = 0; row < pixels.length; row++) {
        	for(int col = 0; col < pixels[row].length; col++) {
        		red = pixels[row][col].getRed();
        		blue = pixels[row][col].getBlue();
        		green = pixels[row][col].getGreen();
        		lowR = red % 4;
        		lowB = blue % 4;
        		lowG = green % 4;
        		lowR *= 64;
        		lowB *= 64;
        		lowG *= 64;
        		pixels[row][col].setRed(lowR);
        		pixels[row][col].setBlue(lowB);
        		pixels[row][col].setGreen(lowG);
        	}
        }
    
                // TODO (1.14): set red, green, and blue values of each 
        		//              pixel to the lowest two bits in source
                /** not yet implemented **/




    
    
        // return the copy
        return copy;
    }

    ////////////////////////////////////////////////////////////////////
    //
    // ACTIVITY 2: Hiding and Revealing a Picture
    //

    // Exercise 2.8
    // **ATTENTION**: do not rewrite this method for Activity 3!
    /**
     * Determines whether the secret picture can be hidden in the source picture.
     * @param source The source picture to hide the secret picture in.
     * @param secret The secret picture.
     * @return true if source and secret images have the same dimensions; otherwise false
     */
    public static boolean canHide(Picture source, Picture secret) {
        
    	// TODO (2.8): determine whether secret can be hidden in source
       if (source.getHeight() == secret.getHeight() && source.getWidth() == secret.getWidth()) {
    	   return true;
       }
        return false;
    }

    // Exercise 2.9
    /**
     * Hides a secret image inside a source image that is the same size by
     * creating a copy of the source picture and setting the lower two bits
     * of each pixel using the data from the secret pixel. Must use the 
     * setLow method.
     * @param source The source image 
     * @param secret The secret image
     * @return a combined image that contains a copy of the source image with 
     *         the secret image hidden within.
     */
    public static Picture hidePicture(Picture source, Picture secret) {
       // TODO (2.9): Create a copy of the source picture and hide a secret picture within the copy. Return the copy.
    	
    	if(!canHide(source, secret)) {
    		return null;
    	}
    	Picture copy = new Picture (source);
    	Pixel[][] pixels = copy.getPixels2D();
    	Pixel [][] secretPixels = secret.getPixels2D();
    	for(int row = 0; row < secretPixels.length; row++) {
        	for(int col = 0; col < secretPixels[row].length; col++) {
        		Color c = secretPixels[row][col].getColor();
        		setLow(pixels[row][col], c);
        	}
    	}
    	return copy;

        
    }

    ////////////////////////////////////////////////////////////////////
    //
    // ACTIVITY 3: Identifying a Hidden Picture
    //

    // Exercise 3.0
    // returns true if secret image can be embedded in source image at the
    // specified row and column without exceeding bounds of source image
    /**
   /**
     * Determines whether the secret picture can be hidden in the source picture at 
     * the specified row and column.
     * @param source The source picture to hide the secret picture in.
     * @param secret The secret picture.
     * @param row the row to start hiding the secret image.
     * @param col the column to start hiding the secret image.
     * @return true if secret can be hidden in source at the specified row and column; otherwise false
     */
    public static boolean canHide(Picture source, Picture secret, int row, int col) {
    	if(row < 0 || col < 0) {
    		return false;
    	}
        if(secret.getHeight()+ row < source.getHeight() && secret.getWidth() + col < source.getWidth()) {
        	return true;
        }
        else {
        	return false;
        }
    }

    // Exercise 3.1
    // creates a new Picture with data from secret hidden in the RGB
    // information in source beginning at pixel location (row, col)
    /**
     * Hides a secret image inside a source image beginning at pixel location (row, col)
     * by creating a copy of the source picture and setting the lower two bits
     * of each pixel using the data from the secret pixel. Must use the 
     * setLow method.
     * @param source The source image 
     * @param secret The secret image
     * @param row the row to start hiding the secret image at
     * @param col the column to start hiding the secret image at
     * @return a combined image that contains a copy of the source image with 
     *         the secret image hidden within.
     */
    public static Picture hidePicture(Picture source, Picture secret, int row, int col) {
        // TODO (3.1): hide a secret picture within a source picture at [row][col]
    	if(!canHide(source, secret, row, col)) {
    		return null;
    	}
    	Picture copy = new Picture (source);
    	Pixel[][] pixels = copy.getPixels2D();
    	Pixel [][] secretPixels = secret.getPixels2D();
    	for(int sRow = 0; sRow < secretPixels.length; sRow++) {
        	for(int sCol = 0; sCol < secretPixels[sRow].length; sCol++) {
        		Color color = secretPixels[sRow][sCol].getColor();
        		setLow(pixels[sRow+ row][sCol + col], color);
        	}
    	}
    	return copy;

        
    }


    // Exercise 3.2
    /**
     * Determines whether two images are exactly the same. Two images are exactly the
     * same if they are the same size and each pixel has the same RGB values.
     * @param image1 image one
     * @param image2 image two
     * @return true if image1 and image are exactly the same;otherwise false.
     */
    public static boolean isSame(Picture image1, Picture image2) {
        // images won't be the same if the height or width is different
        if ((image1.getHeight() != image2.getHeight()) || 
            (image1.getWidth() != image2.getWidth())) {
            return false;
        }
        // get a 2D array of pixels for each image
        Pixel[][] pixels1 = image1.getPixels2D();
        Pixel[][] pixels2 = image2.getPixels2D();
        // TODO (3.2): compare RGB values of pixel pairs using a nested for loop
        for(int row = 0; row < pixels1.length; row++) {
        	for(int col = 0; col < pixels1[0].length; col++) {
        		if (pixels1[row][col].getRed() != pixels2[row][col].getRed() || pixels1[row][col].getBlue() != pixels2[row][col].getBlue()
        				|| pixels1[row][col].getGreen() != pixels2[row][col].getGreen()) {
        			return false;
        		}
        	}
        }
        return true;
    }

    // Exercise 3.3
    /**
     * Finds the list of pixels that differ between two pictures.
     * @param image1 image one
     * @param image2 image two
     * @return An ArrayList of Point objects that differ. If no pixels differ, return an
     *         empty list. A pixel differs, if either the red, green, or blue value
     *         differ for a Pixel.
     */
    public static ArrayList<Point> findDifferences(Picture image1, Picture image2) {
        // create an ArrayList of Point objects
        ArrayList<Point> list = new ArrayList<Point>();
        // return an empty list if the images are different sizes
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) { 
            return list;
        }
        // get a 2D array of pixels for each image
        Pixel[][] pixels1 = image1.getPixels2D();
        Pixel[][] pixels2 = image2.getPixels2D();
        // compare RGB values of pixel pairs using a nested for loop
        // TODO (3.3): create an ArrayList of points where RGB values differ between pixels
        for(int row = 0; row < pixels1.length; row++) {
        	for(int col = 0; col < pixels1[0].length; col++) {
        		if (pixels1[row][col].getRed() != pixels2[row][col].getRed() || pixels1[row][col].getBlue() != pixels2[row][col].getBlue()
        				|| pixels1[row][col].getGreen() != pixels2[row][col].getGreen()) {
        			Point temp = new Point (row, col);
        			list.add(temp);
        		}
        	}
        }
        return list;
    }

    // Exercise 3.4
    // 
    /**
     * Draws a rectangular outline in the color Color.RED (255, 0, 0) around the
     * specified set of points.
     * @param image The source image to draw the rectangular outline on.
     * @param points The points for which the outline is to be drawn around.
     * @return A copy of the source image with the rectangular outline
     */
    public static Picture showDifferentArea(Picture image, ArrayList<Point> points) {
        // create a new picture as a copy of the original
        Picture copy = new Picture(image);
        // return an image without a bounding box if the list of points is empty
        if (points.size() == 0) {
            return copy;
        }
        // find the minimum and maximum row values as well as the minimum and 
        // maximum column values for the bounding box that contains all points
        int minRow, maxRow, minCol, maxCol;
        // initialize the minimums and maximums to the values of the first point
        minRow = points.get(0).getRow();
        maxRow = points.get(0).getRow();
        minCol = points.get(0).getCol();
        maxCol = points.get(0).getCol();
        // TODO (3.4 part1): loop over the points in the list and find the bounding box 
        for(int i = 0; i < points.size(); i++) {
        	if(points.get(i).getRow() < minRow) {
        		minRow = points.get(i).getRow();
        	}
        	if(points.get(i).getRow() > maxRow) {
        		maxRow = points.get(i).getRow();
        	}
        	if(points.get(i).getCol() < minCol) {
        		minCol = points.get(i).getCol();
        	}
        	if(points.get(i).getCol() > maxCol) {
        		maxCol = points.get(i).getCol();
        	}
        }

        // draw an outline of the bounding box using Color.RED (255, 0, 0)
        // get a 2D array of pixels from the copy image
        Pixel[][] pixels = copy.getPixels2D();
        // TODO (3.4 part2): using a loop, draw the two horizontal lines of the bounding box
        for(int row = minRow; row <= maxRow; row += (maxRow-minRow)) {
        	for(int col = minCol; col < maxCol; col++) {
        		pixels[row][col].setRed(255);
        		pixels[row][col].setGreen(0);
        		pixels[row][col].setBlue(0);
        	}
        }




        // TODO (3.4 part3): using a loop, draw the two vertical lines of the bounding box
        for(int col = minCol; col <= maxCol; col += (maxCol-minCol)) {
        	for(int row = minRow; row < maxRow; row++) {
        		pixels[row][col].setRed(255);
        		pixels[row][col].setGreen(0);
        		pixels[row][col].setBlue(0);
        	}
        }




        // return the copy
        return copy;
    }

    ////////////////////////////////////////////////////////////////////
    //
    // ACTIVITY 4: Hiding and Revealing a Text Message
    //
    /**
     * Provided by the College Board, but modified to add uppercase letters and numbers
     * Encodes a string consisting of letters, numbers, and spaces. Each character
     * of the String is encoded into an integer per the following: 
     * 1-26 for A-Z, 27-52 for a-z, 53-62 for 0-9, 63 for space, and 0 for end of string.
     * @param s the string to encode
     * @return An ArrayList of integers including the encoding
     */
    public static ArrayList<Integer> encodeString(String s) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            result.add(alpha.indexOf(s.substring(i, i + 1)) + 1);
        }
        // terminate the list with a zero
        result.add(0);
        // return the list
        return result;
    }

    /**
     * Provided by the College Board, but modified to add uppercase letters and numbers
     * Returns the string represented by the codes in arrayList.
     * The encoded integers use the following rules: 
     * 1-26 for A-Z, 27-52 for a-z, 53-62 for 0-9, 63 for space.
     * @param codes The ArrayList of Integers containing the encoded string.
     * 				Precondition: 
     * @return The decoded string.
     * Pre-Condition: 
     */
    public static String decodeString(ArrayList<Integer> codes) {
        String result = "";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
        for (int i = 0; i < codes.size(); i++) {
            result = result + alpha.substring(codes.get(i) - 1, codes.get(i));
        }
        return result;
    }

    // 
    // 
    // 
    // 
    /**
     * Provided by the College Board
     * Given a number from 0 to 63, creates and returns a 3-element
     * integer array consisting of the integers representing the
     * pairs of bits in the number from right to left.
     * @param num number to be broken up
     * @return bit pairs in number
     */
    private static int[] getBitPairs(int num) {
        int[] bits = new int[3];
        int code = num;
        for (int i = 0; i < 3; i++) {
        	// Get the rightmost 2 bits
            bits[i] = code % 4;
            // shift the bits 2 to the right
            code = code / 4;
        }
        return bits;
    }

    // Exercise 4.2
    /**
     * Hides a string that only contains letters, numbers, and spaces
     * in the upper left corner of the specified picture.
     * @param image picture to hide the string in.
     * @param s string to hide
     * @return picture with hidden string.
     */
    public static Picture hideText(Picture image, String s) {
        // use the method provided by the College Board to create
        // a list of integers that represents an encoded message
        ArrayList<Integer> list = encodeString(s);
        // create a new picture as a copy of the original
        Picture copy = new Picture(image);
        Pixel[] pixel1 = copy.getPixels();
        int [] temp = new int [3];
        
        // loop through the list, split up each letter into bit pairs, then
        for(int i = 0; i < list.size(); i ++) {
        	if(i > pixel1.length-1) {
        		return copy;
        	}
        	temp = getBitPairs(list.get(i));
        	clearLow(pixel1[i]);
        	pixel1[i].setRed(pixel1[i].getRed() + temp[0]);
        	pixel1[i].setGreen(pixel1[i].getGreen() + temp[1]);
        	pixel1[i].setBlue(pixel1[i].getBlue() + temp[2]);
        	
        }
        // TODO (4.2): hide the bit pairs in the RGB info of the image pixels
    

        // return the copy
        return copy;
        //wappy face amog
    }

    // Exercise 4.3
    // 
    /**
     * Reveals the string that is hidden within the specified picture.
     * @param source picture with hidden string
     * @return revealed string
     */
    public static String revealText(Picture source) {
        // declare an arrayList of Integer type to store the encoded text 
        ArrayList<Integer> list = new ArrayList<Integer>();
        Pixel[] pixels = source.getPixels();
        int index = 0;
        int x;
        int tempRed=0;
        int tempGreen;
        int tempBlue;
        // TODO (4.3): loop though the pixels in an image and extract the hidden message
        for(int i = 0; i < pixels.length; i ++) {
        	if(pixels[i].getRed() % 4 == 0 && pixels[i].getGreen() % 4 == 0  && pixels[i].getBlue() % 4 == 0 ) {
                return decodeString(list);
        	} else {
	        	tempRed = pixels[i].getBlue() % 4;
	        	tempGreen = pixels[i].getGreen() % 4;
	        	tempBlue = pixels[i].getRed() % 4;
	        	x = tempRed * 16;
	        	x += tempGreen * 4;
	        	x += tempBlue;
	        	list.add(x);
        	}
        }
        


        // return the revealed text
        return decodeString(list);
    }

    ////////////////////////////////////////////////////////////////////
    //
    // main() method
    //
    // it is recommended that you preserve the sections in main and
    // code up each section as you progress through the student guide.
    //
    // comment out previously completed sections instead of deleting
    // them so that you can preserve the checkpoint functionality.
    //
    // please refer to the Lab student guide for more information.
    //
    public static void main(String[] args) {

        ////////////////////////////////////////////////////
        // ACTIVITY 1: Exploring Color

        /*
        // Exercise 1.8
        Picture beach1dot8 = new Picture("beach.jpg");
        beach1dot8.setTitle("1.8 Beach Original");
        beach1dot8.explore();
        Picture copy1dot8 = testClearLow(beach1dot8);
        copy1dot8.setTitle("1.8 Beach after testClearLow");
        copy1dot8.explore();

        // Exercise 1.12
        Picture beach1dot12 = new Picture("beach.jpg");
        beach1dot12.setTitle("1.12 Beach Original");
        beach1dot12.explore();
        Color eastlakeRed = new Color(107, 0, 36);
        Picture copy1dot12 = testSetLow(beach1dot12, eastlakeRed);
        copy1dot12.setTitle("1.12 Beach after testSetLow");
        copy1dot12.explore();

        // Exercise 1.14
        Picture copy1dot14 = revealPicture(copy1dot12);
        copy1dot14.setTitle("1.14 revealPicture");
        copy1dot14.explore();
        **/

        ////////////////////////////////////////////////////
        // ACTIVITY 2: Hiding and Revealing a Picture

        /*
        // Exercise 2.8 
        Picture beach2dot8 = new Picture("beach.jpg");
        Picture arch2dot8 = new Picture("arch.jpg");
        System.out.println("beach same size as arch? " + canHide(beach2dot8, arch2dot8));

        // Exercise 2.9
        Picture swan2dot9 = new Picture("swan.jpg");
        Picture gorge2dot9 = new Picture("gorge.jpg");
        System.out.println("swan same size as gorge? " + canHide(swan2dot9, gorge2dot9));
        swan2dot9.setTitle("2.9 Swan Original");
        swan2dot9.explore();
        gorge2dot9.setTitle("2.9 Gorge Original");
        gorge2dot9.explore();
        Picture combined2dot9 = null;
        if (canHide(swan2dot9, gorge2dot9)) {
            combined2dot9 = hidePicture(swan2dot9, gorge2dot9);
            combined2dot9.setTitle("2.9 Gorge hidden in Swan");
            combined2dot9.explore();
        }

        // Exercise 2.11
        if (combined2dot9 != null) {
            Picture revealed2dot11 = revealPicture(combined2dot9);
            revealed2dot11.setTitle("2.11 Gorge revealed");
            revealed2dot11.explore();
        }
        */

        ////////////////////////////////////////////////////
        // ACTIVITY 3: Identifying a Hidden Picture

        /*
        // Exercise 3.1
        Picture beach3dot1 = new Picture("beach.jpg");
        beach3dot1.setTitle("3.1 Beach Original");
        beach3dot1.explore();
        // hide the robot and flower pictures in the beach picture
        Picture robot3dot1 = new Picture("robot.jpg");
        Picture flower3dot1 = new Picture("flower1.jpg");
        if (canHide(beach3dot1, robot3dot1, 65, 208) && canHide(beach3dot1, flower3dot1, 280, 110)) {
            Picture temp = hidePicture(beach3dot1, robot3dot1, 65, 208);
            Picture hidden = hidePicture(temp, flower3dot1, 280, 110);
            hidden.setTitle("3.1 Beach with Hidden Pictures");
            hidden.explore();
            Picture unhidden = revealPicture(hidden);
            unhidden.setTitle("3.1 Beach with Revealed Pictures");
            unhidden.explore();
        }

        // Exercise 3.2
        Picture swanCopy1 = new Picture("swan.jpg");
        Picture swanCopy2 = new Picture("swan.jpg");
        System.out.print("Swan1 and swan2 are the same? ");
        System.out.println(isSame(swanCopy1, swanCopy2));
        swanCopy2 = testClearLow(swanCopy2);
        System.out.print("Swans are the same after clearLow on swan2? ");
        System.out.println(isSame(swanCopy1, swanCopy2));
		
        // Exercise 3.3
        Picture archCopy1 = new Picture("arch.jpg");
        Picture archCopy2 = new Picture("arch.jpg");
        Picture koala3dot3 = new Picture("koala.jpg");
        Picture robot3dot3 = new Picture("robot.jpg");
        ArrayList<Point> pointList = findDifferences(archCopy1, archCopy2);
        System.out.print("Number of pixels different between arch and arch: ");
        System.out.println(pointList.size());
        pointList = findDifferences(archCopy1, koala3dot3);
        System.out.print("Number of pixels different between arch and koala: ");
        System.out.println(pointList.size());
        archCopy2 = hidePicture(archCopy1, robot3dot3, 65, 102);
        pointList = findDifferences(archCopy1, archCopy2);
        System.out.print("Number of pixels different between arch with hidden ");
        System.out.print("image and original arch image: ");
        System.out.println(pointList.size());
        archCopy1.setTitle("3.3 Original Arch");
        archCopy1.explore();
        archCopy2.setTitle("3.3 Arch with Hidden Image");
        archCopy2.explore();
        Picture revealed3dot3 = revealPicture(archCopy2);
        revealed3dot3.setTitle("3.3 Revealed Hidden Picture");
        revealed3dot3.explore();

        // Exercise 3.4
        Picture templeCopy1 = new Picture("femaleLionAndHall.jpg");
        Picture robot3dot4 = new Picture("robot.jpg");
        Picture flower3dot4 = new Picture("flower1.jpg");
        // hide pictures
        Picture templeCopy2 = hidePicture(templeCopy1, robot3dot4, 50, 300);
        Picture templeCopy3 = hidePicture(templeCopy2, flower3dot4, 115, 275);
        templeCopy3.setTitle("3.4 Original Female Lion and Hall");
        templeCopy3.explore();
        if (!isSame(templeCopy1, templeCopy3)) {
            ArrayList<Point> pointList2 = findDifferences(templeCopy1, templeCopy3);
            System.out.print("Number of pixels different between temple with hidden ");
            System.out.print("images and original arch image: ");
            System.out.println(pointList2.size());
            Picture templeCopy4 = showDifferentArea(templeCopy1, pointList2);
            templeCopy4.setTitle("3.4 Show Different Area");
            templeCopy4.explore();
            Picture unhidden = revealPicture(templeCopy3);
            unhidden.setTitle("3.4 Temple with Hidden Pictures");
            unhidden.explore();
        }
       
		*/
        ////////////////////////////////////////////////////
        // ACTIVITY 4: Hiding and Revealing a Text Message

        /*
        // Exercise 4.4
        Picture beach4dot4 = new Picture("beach.jpg");
        beach4dot4.setTitle("4.4 Original Beach Image");
        beach4dot4.explore();
        Picture hiddenText = hideText(beach4dot4, "HAVE FUN STORMING THE CASTLE");
        hiddenText.setTitle("4.4 Beach Image with Hidden Text");
        hiddenText.explore();
        String secret = revealText(hiddenText);
        if (secret.equals("HAVE FUN STORMING THE CASTLE")) {
            System.out.println("If only you had a wheelbarrow.");
        }
        else {
            System.out.println("Do you think it worked? (It didn't.)");
        }
        */

        ////////////////////////////////////////////////////
    }

}
