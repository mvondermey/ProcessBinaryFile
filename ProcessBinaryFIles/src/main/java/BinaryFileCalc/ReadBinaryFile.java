package BinaryFileCalc;
/**
 * 
 */



/**
 * @author martinvondermey
 *
 */

import java.awt.Point;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.junit.rules.Stopwatch;


public class ReadBinaryFile {
	//
	// The strategy is to read in the list of points and then do calculations with them in one go. 
	//  Would the list be much larger then calculations could be done in one go while reading in. But would make the code
	//  more difficult to read
	//
	static Vector<Point> Closest_10_Points;
	static Vector<Point> Furthest_20_Points;
	//
	public static void main(String[] args) {
	//	
	Vector<Point> PointList = ReadBinaryFile();
	//
	Calculate_Closest_Furthest_Points(PointList);
	//
	}
	//
	public Vector<Point> GetFurthest20Points(){
		return Furthest_20_Points;
	}
	//
	public Vector<Point> GetCloses10Points(){
		return Closest_10_Points;
	}
	//
	static void Calculate_Closest_Furthest_Points(Vector<Point> pointList) {
	// 
	// Calculate distance to the given points and use hashmap for sorting.
	//
	short XCoordinatePoint = -200;
	short YCoordinatePoint =  300;
	//
	short XCoordinatePoint2 =  1000;
	short YCoordinatePoint2 =  25;
	//
	Point ReferencePoint = new Point(-200,300);
	Point ReferencePoint2 = new Point(1000,25);
	// 
	//
	// Now crate HashMap for sorting
	//
	HashMap<Integer,Double> myMapClosest = new HashMap<Integer,Double>();
	HashMap<Integer,Double> myMapFurthest = new HashMap<Integer,Double>();
	//
	int count = -1;
	//
	// Calculate Distance and store in HashMap
	//
	System.out.println("=> Calculate Distance \n");
	//
	long startTime = System.currentTimeMillis();
	//
	for (Point myPoint : pointList)
	{
		//
		double myDistance = myPoint.distanceSq(ReferencePoint);
		double myDistance2 = myPoint.distanceSq(ReferencePoint2);
		//
		count++;
		myMapClosest.put(count,myDistance);
		myMapFurthest.put(count,myDistance2);
		//
	    //System.out.println(">"+myPoint.getX()+" "+myPoint.getY()+" distance "+myDistance+" distance2 "+myDistance2);
	    //
	    //
	}
	//
    System.out.println("Time for calculating distance (s)"+ TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - startTime))+"\n");
	//
	// Sort Hashmap. Use Comparator
    /*
	System.out.println("\n Before sorting closest");
    Set seto = myMapClosest.entrySet();
    Iterator iteratoro = seto.iterator();
    while(iteratoro.hasNext()) {
         Map.Entry meo = (Map.Entry)iteratoro.next();
         System.out.print(meo.getKey() + ": ");
         System.out.println(meo.getValue());
    }
	*/
    //
    /*
    System.out.println("\n Before sorting furthest");
    Set seto2 = myMapFurthest.entrySet();
    Iterator iteratoro2 = seto2.iterator();
    while(iteratoro2.hasNext()) {
         Map.Entry meo = (Map.Entry)iteratoro2.next();
         System.out.print(meo.getKey() + ": ");
         System.out.println(meo.getValue());
    }
    */
	//
	System.out.println("=>Do sorting \n");
	//
	startTime = System.currentTimeMillis();
	Map<Integer, String> mapC = sortByValuesAscending(myMapClosest);
    System.out.println("Time for sorting Closest (s)"+ TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - startTime)));
    //
    startTime = System.currentTimeMillis();
	Map<Integer, String> mapF = sortByValuesDescending(myMapFurthest);
    System.out.println("Time for sorting Furthest (s)"+TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - startTime)));
	//
	// Clear up memory
	//
	myMapClosest.clear();
	myMapFurthest.clear();
	//
	Closest_10_Points = new Vector<Point>();
	Furthest_20_Points = new Vector<Point>();
	//

	int number1=0;
	   System.out.println("\nAfter Sorting Closest:");
	      Set setC = mapC.entrySet();
	      Iterator iterator = setC.iterator();
	      while(iterator.hasNext() && number1 <10) {
	    	   number1++;
	           Map.Entry me = (Map.Entry)iterator.next();
	           //
	           System.out.print(me.getKey() + ": ");
	           System.out.println(me.getValue());
	           //
	           Closest_10_Points.add(pointList.elementAt((int) me.getKey()));
	           //
	      }
	      //
	      	System.out.println("\n");
//
	  	for (Point myPoint : Closest_10_Points)
		{
	  	System.out.println("X= "+myPoint.getX()+" Y= "+myPoint.getY());
		}
	      //
	  	int number2=0;
		   System.out.println("\nAfter Sorting Furthest:");
		      Set setF = mapF.entrySet();
		      Iterator iterator2 = setF.iterator();
		      while(iterator2.hasNext() && number2 <20) {
		    	   number2++;
		           Map.Entry me = (Map.Entry)iterator2.next();
		           System.out.print(me.getKey() + ": ");
		           System.out.println(me.getValue());
		           //
		           Furthest_20_Points.add(pointList.elementAt((int) me.getKey()));
		           //
		      }
//
		      	System.out.println("\n");
			  	for (Point myPoint : Furthest_20_Points)
				{
			  	System.out.println("X= "+myPoint.getX()+" Y= "+myPoint.getY());
				}
	//
}
	//
	//
private static HashMap sortByValuesAscending(HashMap map) {
	//
	// Sort in descending order
	//
    LinkedList list = new LinkedList(map.entrySet());
    // Defined Custom Comparator here
    Collections.sort(list, new Comparator() {
         @Override
		public int compare(Object o1, Object o2) {
        	 double value1 = (double) ((Map.Entry) (o1)).getValue();
        	 double value2 = (double) ((Map.Entry) (o2)).getValue();
        	 //
        	 int myCompare = value1 > value2 ? 1 : (value1 < value2 ? -1 : 0);
        	 //
        	 //System.out.println(value1+" "+value2+" "+myCompare);
        	 //
        	 //
            return myCompare;
         }
         //
    });

    // Here I am copying the sorted list in HashMap
    // using LinkedHashMap to preserve the insertion order
    HashMap sortedHashMap = new LinkedHashMap();
    for (Iterator it = ((java.util.List<Point>) list).iterator(); it.hasNext();) {
           Map.Entry entry = (Map.Entry) it.next();
           sortedHashMap.put(entry.getKey(), entry.getValue());
    } 
    return sortedHashMap;
}
//
private static HashMap sortByValuesDescending(HashMap map) {
	//
	// Sort in ascending order
	//
    LinkedList list = new LinkedList(map.entrySet());
    // Defined Custom Comparator here
    Collections.sort(list, new Comparator() {
         @Override
		public int compare(Object o1, Object o2) {
        	 double value1 = (double) ((Map.Entry) (o1)).getValue();
        	 double value2 = (double) ((Map.Entry) (o2)).getValue();
        	 //
        	 int myCompare = value2 > value1 ? 1 : (value2 < value1 ? -1 : 0);
        	 //
        	 //System.out.println(value1+" "+value2+" "+myCompare);
        	 //
        	 //
            return myCompare;
         }
         //
    });

    // Here I am copying the sorted list in HashMap
    // using LinkedHashMap to preserve the insertion order
    HashMap sortedHashMap = new LinkedHashMap();
    for (Iterator it = ((java.util.List<Point>) list).iterator(); it.hasNext();) {
           Map.Entry entry = (Map.Entry) it.next();
           sortedHashMap.put(entry.getKey(), entry.getValue());
    } 
    return sortedHashMap;
}
//
public static Vector<Point>  ReadBinaryFile(){
		//
		// Read File into memory as Vector of points
		//
		InputStream file = null;
		try {
			file = new FileInputStream("C:/Users/martinvondermey/Desktop/Problem 2 - Distance/points");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//
		int count = -1;
        short XCoordinate = 0;
        short YCoordinate = 0;
		//
        // Create Vector of 2-dim points
        //
		Vector<Point> PointList = new Vector<Point>();
		//	
		try (DataInputStream fis = new DataInputStream(file)) {
			//
		       // available stream to be read
	         while(fis.available()>0  && count < 100000000 )
	         {
	            // read two bytes from data input, return short
	            short k = fis.readShort();
	            count++;
	            //
	            // print short value
	            if (count%1000000==0) System.out.print("Read in "+count+" numbers\n");
	            //
	            // Create 2-dim Points from X-corrdinate and Y-Coordinate
	            //
	            if ( count %2 == 0 ){
	            	XCoordinate = k;	            	
	            } else {
	            	YCoordinate = k;
	            	Point myPoint = new Point(XCoordinate, YCoordinate);
	            	//System.out.print("=========> Point= " + myPoint.getX()+" "+myPoint.getY()+"\n");
	            	//
	            	//Add point to vector
	            	//
	            	PointList.add(myPoint);
	            	//
	            }

	         }

		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		System.out.println("Finished reading "+count+" entries from file \n");
		//
		return PointList;
	}
}
