package io.practice;

public class MathUtils {

	
	// Default state of JUnit is PASS
	// Junit enginer will directly call methods @Test annotated on top
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public int substract(int a, int b) {
		return a-b;
	}
	
	public int multiply(int a, int b) {
		return a*b;
	}
	
	public int devide(int a, int b) {
		return a/b;
	}
	
	
	public double computeCircleArea(double radius) {
		return Math.PI*radius*radius;
	}

}
