package io.practice;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // PER_METHOD is default
class MathUtilsTest {

	MathUtils mathUtils;
	
	
	TestInfo testInfo;
	TestReporter testReporter;
	
	// Static because before creating the object we cannot call beforeAllInit method
	@BeforeAll
	void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo=testInfo;
		this.testReporter=testReporter;
		
		
		// we can also write this testinfo and testreporter in each test 
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+ testInfo.getTags());

		mathUtils=new MathUtils();
	}

	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning up...");
	}
	
	@Nested
	@DisplayName("ADD METHOD GLOBAL")
	@Tag("Math") // tag used to run test based on specific tag.. configure in run options
	class AddTest {
		@Test
		@DisplayName ("ADD METHOD +ve Num")
		void testAdd() {

			assertEquals(30, mathUtils.add(10, 20), ()->"add method of two numbers");
			
		}
		
		@Test
		@DisplayName ("ADD METHOD for -ve")
		void testAdd1() {

			assertEquals(-10, mathUtils.add(10, -20));
			
		}
	}
	
	
	
	@Test
	@DisplayName("Multiply Method")
	void multiplyTest() {
		//assertEquals(4, mathUtils.multiply(2, 2));
		assertAll(
				()-> assertEquals(4, mathUtils.multiply(2, 2)),
				()-> assertEquals(0, mathUtils.multiply(2, 0)),
				()-> assertEquals(-2, mathUtils.multiply(-2, 1))
				);
	}
	
	@Test
	void testDevide() {
		boolean isServerUp= false;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class,  ()->mathUtils.devide(1, 0),"Divide by 0");
	}
	
	@Test
	@Tag("Math")
	void testDevideTag() {
		
//		System.out.println("Running "+testInfo.getDisplayName()+" with tags "+ testInfo.getTags());
//		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+ testInfo.getTags());
		boolean isServerUp= false;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class,  ()->mathUtils.devide(1, 0),"Divide by 0");
	}
	
	@Test
	@EnabledOnOs(OS.LINUX)
	void testDevide1() {
		
		assertThrows(ArithmeticException.class,  ()->mathUtils.devide(1, 0),"Divide by 0");
	}
	
	@Test
	@EnabledOnJre(JRE.JAVA_11)
	@DisplayName("This test only runs in Java 11 version")
	void testDevide2() {
		
		assertThrows(ArithmeticException.class,  ()->mathUtils.devide(1, 0),"Divide by 0");
	}
	
	
	@Test
	@Disabled
	@DisplayName("Test Disabled please ignore...")
	void newTestDisabled() {
		
		assertThrows(ArithmeticException.class,  ()->mathUtils.devide(1, 0),"Divide by 0");
	}
	
	@RepeatedTest(3)
	void testComputeCircleRadius() {
		
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10),"Should return circle area right value");
	
	}

}
