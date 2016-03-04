/*
	A Java program designed to find the worst possible way to cast an integer to a float without explicitly casting it
*/


public class caster {

	public static void main (String args[])
    {
      int y = 5;      //556848164    
      float x = y;      //Divides by 2 only after setting as float
      x /= 2;
      int z;
      
      float q = 30;
      long r = (long) q;
      
      String numberWhole = Long.toString((long)(x*=10)); 	// Convert result of division into a string, also sets x to proper value
      														// Cast as a long, in case value is too large for int
      //System.out.println ("Whole number: " + numberWhole);
    
      String numberLeftHalf;
      String numberRightHalf;
      
      // Handling for decimals in result
      // Not necessary for this operation, but can be useful with others
      if (numberWhole.indexOf('.') >= 0) 
      {
    	numberLeftHalf = numberWhole.substring(0, numberWhole.indexOf('.'));
      	numberRightHalf = numberWhole.substring(numberWhole.indexOf('.') + 1);
    	  
      	z = Integer.parseInt(numberLeftHalf);

        System.out.println ("x = " + x);
      	
      	System.out.print ("z = " + z);
      
      	if (Integer.parseInt(numberRightHalf) != 0) // If decimal is unnecessary, don't display it
      	{
      		z = Integer.parseInt(numberRightHalf);
      		System.out.print ("." + z);
      	}
      }
      
      else 
      {
        System.out.println ("x = " + x);  
    	  
        if (numberWhole.length() >= 8) 	// Test is value is small enough to print as one integer
        								// If not, split into halves and print separately
        {
      	  int tempLength = numberWhole.length();
      	  numberLeftHalf = numberWhole.substring(0, tempLength/2);
      	  numberRightHalf = numberWhole.substring(tempLength/2);
      	  
      	  z = Integer.parseInt(numberLeftHalf);
      	  System.out.print ("z = " + z);
      	  z = Integer.parseInt(numberRightHalf);
    	  System.out.print (numberRightHalf); // If right half is all zeroes, converting to int will remove zeroes
        }
        else
        {
        	z = Integer.parseInt(numberWhole);
        	System.out.println ("z = " + z);
        }
      }
      
    }

}
