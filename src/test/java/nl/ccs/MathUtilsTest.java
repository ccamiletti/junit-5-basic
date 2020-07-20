package nl.ccs;

import static org.junit.Assume.assumeFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(Lifecycle.PER_CLASS)
class MathUtilsTest {

	MathUtils mathUtils = null;
	TestInfo testInfo; 
	TestReporter testReport;
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReport) {
		this.testInfo = testInfo;
		this.testReport = testReport;
		mathUtils = new MathUtils();
	}

	@BeforeAll
	 void beforeAllinit() {
		System.out.println("this need to be run before all");
	}
	
	//@AfterEach
	@Disabled
	void cleanUp() {
		System.out.println("Cleannig up...");
	}
	
	@Test
	@DisplayName("test add method")
	@Tag("Math")
	void test() {
		int actual = mathUtils.math(1, 1);
		int expected = 2;
		System.out.println("Running: " + testInfo.getDisplayName());
		System.out.println("Tag: " + testInfo.getTags());

		assertEquals(expected, actual, "the add method should add 2 numbers");
	}

	@Test
	void notEquals() {
		int actual = mathUtils.math(1, 1);
		int expected = 3;
		assertNotEquals(expected, actual, "the add method should add 2 numbers");
	}

	@Test
	void testComputeCircleRadius() {
		Double actual = mathUtils.computeCircleArea(10);
		Double expected = 314.1592653589793;
		assertEquals(expected, actual, "should return right circle area");
	}
	
	@Test
	void testDevide() {
		System.out.println("assuming");
		assumeFalse(Boolean.TRUE);
		assertThrows(ArithmeticException.class, () -> mathUtils.devide(1, 0), "divide by zero should throw");
	}
	
	@Test
	@Disabled
	void testDisable() {
		fail("this test should be disabled");
	}
		
	@Test
	@EnabledOnOs(OS.LINUX)
	@DisplayName("isLinux")
	void testLinux() {
		System.out.println("executing on linux system operating");
	}

	
	@Test
	@EnabledOnJre(JRE.JAVA_11)
	@DisplayName("testIsJava11")
	void testJre11() {
		int current = mathUtils.devide(4, 2);
		int expected = 2;
		assertEquals(expected, current, () -> "should return div: " + expected + " but it returned: " + current);
		System.out.println("executing with java 11");
	}
	
	
	@Nested
	@DisplayName("add method")
	class AddTest {
		
		@Test
		@DisplayName("when adding two positive numbers")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1,1));
		}

		@Test
		@DisplayName("when adding two negative numbers")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1));
		}
	}
	
	@Test
	@DisplayName("mutiples")
	void testMultiple() {
		assertAll(
				() -> assertEquals(-2, mathUtils.add(-1, -1), "one"), 
				() -> assertEquals(-2, mathUtils.add(-1, -1), "two"),
				() -> assertEquals(-2, mathUtils.add(-1, -1), "three")
		);		
	}
	
	
	@RepeatedTest(3)
	void testRepeat(RepetitionInfo repetitionInfo) {
		System.out.println("for console: " + repetitionInfo.getCurrentRepetition());
		testReport.publishEntry("this message is not for the console");
	}
	
	
	
}
