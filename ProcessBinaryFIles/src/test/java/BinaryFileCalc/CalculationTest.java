package BinaryFileCalc;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Vector;

import org.junit.Test;

//
// Simple Testing class, take 2 Points and compares result to expected.
// 

public class CalculationTest {

	@Test
	public void test() {
		
		ReadBinaryFile myTest = new ReadBinaryFile();
		
		Vector<Point> vectorTest = new Vector<Point>();

		vectorTest.add(new Point(20,30));
		vectorTest.add(new Point(1000,3000));

		
		ReadBinaryFile.Calculate_Closest_Furthest_Points(vectorTest);
	
		
		assertEquals("Must be 20.", 20, (int) myTest.GetCloses10Points().elementAt(0).getX());
		assertEquals("Must be 30.", 30, (int) myTest.GetCloses10Points().elementAt(0).getY());
		
		assertEquals("Must be 1000.", 1000, (int) myTest.GetFurthest20Points().elementAt(0).getX());
		assertEquals("Must be 3000.", 3000, (int) myTest.GetFurthest20Points().elementAt(0).getY());
		
		
	
	}

}
