 * StackTest.java
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Lab 3
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void testStack() {
		Stack<String> L = new Stack<>(new String[] { "1", "2", "3" });
		// based off LIFO top is 3 bottom is 1
		// test 1
		assertEquals("3", L.peek());
		// test 2
		L.push("5");
		assertEquals("5", L.peek());
		// test 3
		L.pop();
		assertEquals("3", L.peek());
	}

	@Test
	void testStackTArray() {
		String[] arr = new String[] { "1", "2", "3" };
		// based off LIFO top is 3 bottom is 1
		Stack<String> L = new Stack<>(arr);
		// test 1
		assertEquals("3 2 1 " + "\n", L.toString());
		// test 2
		assertEquals("3", L.peek());
		// test 3
		L.push("5");
		assertEquals("5", L.peek());
	}

	@Test
	void testStackStackOfT() {
		// test 1
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3" });
		Stack<String> List2 = new Stack<>(new String[] { "1", "2", "3", "4" });
		Stack<String> List3 = new Stack<>(new String[] { "1", "2", "3", "4", "5" });
		Stack<String> L = new Stack<>(List1);
		assertEquals("3 2 1 \n", L.toString());
		// test 2
		Stack<String> List = new Stack<>(List2);
		assertEquals("4", List.peek());
		// test 3
		Stack<String> sadList = new Stack<>(List3);
		List2.push("A");
		assertEquals("A", List2.peek());
	}

	@Test
	void testPeek() {
		Stack<String> L = new Stack<>();
		assertThrows(NoSuchElementException.class, () -> {
			L.peek();
		});
		L.push("A");
		assertEquals("A", L.peek()); // test 2
		L.push("B");
		assertTrue(L.peek().equals("B")); // test 3
		L.push("C");
		assertNotEquals("B", L.peek()); // test 4
	}

	@Test
	void testGetSize() {
		Stack<String> L = new Stack<>();
		// test 1
		L.push("A");
		assertEquals(1, L.getSize());
		// test 2
		L.push("B");
		L.push("C");
		assertEquals(3, L.getSize());
		// test 3
		L.pop();
		L.pop();
		L.pop();
		assertEquals(0, L.getSize());
	}

	@Test
	void testIsEmpty() {
		Stack<String> L = new Stack<>();
		// test 1
		assertEquals(true, L.isEmpty());
		// test 2
		L.push("A");
		assertEquals(false, L.isEmpty());
		// test 3
		L.pop();
		assertEquals(true, L.isEmpty());
	}

	@Test
	void testPush() {
		Stack<String> L = new Stack<>();
		// test 1
		assertEquals(0, L.getSize());
		// test 2
		L.push("A");
		assertEquals(1, L.getSize());
		L.push("B");
		// test 3
		L.push("C");
		L.push("D");
		L.push("E");
		L.push("F");
		L.pop();
		assertEquals(5, L.getSize());
	}

	@Test
	void testPop() {
		Stack<String> L = new Stack<>();
		// testing precondition or test 1:
		assertThrows(NoSuchElementException.class, () -> {
			L.pop();
		});
		// test 2
		L.push("1");
		L.push("6");
		L.pop();
		assertEquals("1", L.peek());
		// test 3
		L.push("3");
		L.push("2");
		L.pop();
		assertEquals("3", L.peek());
	}

	@Test
	void testToString() {
		Stack<String> L = new Stack<>();
		// test 1
		assertEquals("\n", L.toString());
		// test 2
		L.push("1");
		L.push("5");
		L.push("4");
		assertEquals("4 5 1 " + "\n", L.toString());
		// test 3
		L.pop();
		L.pop();
		assertEquals("1 " + "\n", L.toString());
	}

	@Test
	void testEqualsObject() {
		// test1
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3" });
		Stack<String> List2 = new Stack<>(new String[] { "1", "2", "3", "4" });
		assertEquals(false, List1.equals(List2));
		// test 2
		List1.push("4");
		assertEquals(true, List1.equals(List2));
		// test 3
		List1.pop();
		List2.pop();
		assertEquals(true, List1.equals(List2));
	}

	@Test
	void testReverseStack() {
		Stack<String> q = new Stack<>();
		// test 1
		q.push("A");
		q.push("B");
		q.push("C");
		assertEquals("A B C \n", q.reverseStack());
		// test 2
		q.pop();
		q.pop();
		assertEquals("A \n", q.reverseStack());
		// test 3
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3" });
		List1.push("A");
		List1.push("B");
		List1.push("C");
		assertEquals("1 2 3 A B C \n", List1.reverseStack());

	}

	@Test

	void testIsSorted() {
		// test 1
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3" });
		assertTrue(List1.isSorted());
		// test 2
		List1.push("6");
		assertTrue(List1.isSorted());
		// test 3
		List1.push("5");
		assertFalse(List1.isSorted());
		Stack<String> List2 = new Stack<>(new String[] {});
		assertTrue(List2.isSorted());

	}

	@Test
	void testLinearSearch() {
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3" });
		// test 1
		assertTrue(List1.linearSearch("1"));
		// test 2
		Stack<String> List = new Stack<>(new String[] {});
		assertFalse(List.linearSearch("1"));
		// test 3
		assertFalse(List1.linearSearch("5"));
	}

	@Test
	void testBinarySearch() {
		//test 1
		Stack<String> List1 = new Stack<>(new String[] { "1", "2", "3","4","5","6" });
		Stack<String> List2 = new Stack<>(new String[] { "4", "2", "10" });
		assertThrows(IllegalStateException.class, () -> { 
			List2.binarySearch("2");
			});
		assertTrue(List1.binarySearch("4"));
		//test 2
		List1.push("7");
		assertTrue(List1.binarySearch("7"));
		//test 3
		List1.pop();
		assertFalse(List1.binarySearch("7"));
		
	}

}
