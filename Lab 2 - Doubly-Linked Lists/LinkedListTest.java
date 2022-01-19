
/**
 * LinkListTest.java
 * tests doubly-linked list class and methods
 * @author Alex Benny
 * @author Daniel Tran
 */import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void testLinkedList() {
		LinkedList<String> L = new LinkedList<>(new String[] {"1", "2", "3"});
		//test 1
		assertEquals("1",L.getFirst());
		//test 2
		LinkedList<String> List = new LinkedList<>(new String[] {"1", "2", "3"});
		assertEquals("3",List.getLast());
		//test 3
		LinkedList<String> List1 = new LinkedList<>(new String[] {"ALEX","DANIEL"});
		assertEquals("ALEX",List1.getFirst());
	}

	@Test
	void testLinkedListTArray() {
		//test 1
		String[] arr = new String[]{"1", "2", "3"};
		LinkedList<String> L = new LinkedList<>(arr);
		assertEquals("1 2 3 "+"\n",L.toString());
		//test 2
		String[] array = new String[]{"1", "2", "3"};
		LinkedList<String> List = new LinkedList<>(array);
		assertEquals("3",List.getLast());
		//test 3
		String[] Array = new String[]{"1", "2", "3"};
		LinkedList<String> List2 = new LinkedList<>(array);
		List2.addFirst("A");
		assertEquals("A",List2.getFirst());
	}

	@Test
	void testLinkedListLinkedListOfT() {
		//test 1
        LinkedList<String> List1 = new LinkedList<>(new String[] {"1", "2", "3"});
        LinkedList<String> List2 = new LinkedList<>(new String[] {"1", "2", "3", "4"});
        LinkedList<String> List3 = new LinkedList<>(new String[] {"1", "2", "3", "4","5"});
		LinkedList<String> L = new LinkedList<>(List1);
		assertEquals("1 2 3 \n",L.toString());
		//test 2
		
		LinkedList<String> List = new LinkedList<>(List2);
		assertEquals("4",List.getLast());
		//test 3
		
		LinkedList<String> sadList = new LinkedList<>(List3);
		List2.addFirst("A");
		assertEquals("A",List2.getFirst());
	}

	@Test
	void testGetFirst() {
		LinkedList<String> L = new LinkedList<>();
        assertThrows(NoSuchElementException.class, ()->{L.getFirst();}); // Precondition where length is 0
        L.addFirst("A");
        assertEquals("A", L.getFirst()); //test 2
        L.addLast("B");
        assertFalse(L.getFirst().equals("B")); //test 3
        L.removeLast();
        assertNotEquals("B", L.getFirst()); //test 4
	}

	@Test
    void testGetLast() {
        LinkedList<String> L = new LinkedList<>();
        assertThrows(NoSuchElementException.class, ()->{L.getLast();}); //test 1
        L.addFirst("A");
        assertEquals("A", L.getLast()); //test 2
        L.addLast("B");
        assertTrue(L.getLast().equals("B")); //test 3
        L.removeLast();
        assertNotEquals("B", L.getLast()); //test 4
    }

	@Test
	void testGetIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertThrows(NullPointerException.class, ()->{L.getIterator();});
		//test 2
		L.addFirst("A");
		L.positionIterator();
		assertEquals("A",L.getIterator());
		//test 3
		L.addFirst("B");
		L.addFirst("C");
		L.addFirst("D");
		L.positionIterator();
		L.advanceIterator();
		assertEquals("C",L.getIterator());


	}

	@Test
	void testGetLength() {
		LinkedList<String> L = new LinkedList<>();
		//test 1
		L.addFirst("A");
		assertEquals(1, L.getLength());
		//test 2
		L.addFirst("B");
		L.addFirst("C");
		assertEquals(3, L.getLength());
		//test 3
		L.removeLast();
		L.removeLast();
		L.removeLast();
        assertEquals(0, L.getLength());
	}

	@Test
	void testIsEmpty() {
		LinkedList<String> L = new LinkedList<>();
		//test 1
		assertEquals(true, L.isEmpty());
		L.addFirst("A");
		//test 2
		assertEquals(false, L.isEmpty());
		//test 3
		L.removeLast();
        assertEquals(true, L.isEmpty());
		
	}

	@Test
	void testOffEnd() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertEquals(true,L.offEnd());
		//test 2
		L.addFirst("A");
		L.positionIterator();
		assertEquals(false,L.offEnd());
		//test 3
		L.removeFirst();
		L.positionIterator();
		assertEquals(true,L.offEnd());
		
	
	}

	@Test
	void testAddFirst() {
		LinkedList<String> L = new LinkedList<>();
		//test 1
		assertEquals(0, L.getLength());
		//test 2
		L.addFirst("A");
		assertEquals(1, L.getLength());
		L.addFirst("B");
		//test 3
		L.addFirst("C");
		L.addFirst("D");
		L.addFirst("E");
		L.addFirst("F");
		L.removeLast();
		assertEquals(5, L.getLength());
		
	}

	@Test
	void testAddLast() {
		LinkedList<String> L = new LinkedList<>();
		//test 1
		assertEquals(0, L.getLength());
		//test 2
		L.addFirst("A");
		assertEquals(1, L.getLength());
		L.addFirst("B");
		//test 3
		L.addFirst("C");
		L.addFirst("D");
		L.addFirst("E");
		L.removeLast();
		assertEquals(4, L.getLength());
	}

	@Test
	void testAddIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertThrows(NullPointerException.class, ()->{L.addIterator(null);});
		//test 2
		L.addFirst("A");
		L.positionIterator();
		L.addIterator("B");
		assertEquals("A",L.getIterator());
		//test 3
		
		L.advanceIterator();
		assertEquals("B",L.getIterator());
		
	}

	@Test
    void testRemoveFirst() {
        LinkedList<String> L = new LinkedList<>();
        //testing precondition or test 1:
        assertThrows(NoSuchElementException.class, ()->{L.removeFirst();});
        //test 2
        L.addFirst("1");
        L.addFirst("6");
        L.removeFirst();
        assertEquals("1", L.getFirst());
        //test 3
        L.addFirst("3");
        L.addFirst("2");
        L.removeFirst();
        assertEquals("3", L.getFirst());
        
    }

	@Test
	void testRemoveLast() {
		LinkedList<String> L = new LinkedList<>();
        //testing precondition or test 1:
        assertThrows(NoSuchElementException.class, ()->{L.removeLast();});
        //test 2
        L.addFirst("1");
        L.addFirst("5");
        L.addFirst("4");
        L.removeLast();
        assertEquals("5", L.getLast());
        //test 3
        L.addFirst("3");
        L.addFirst("2");
        L.removeLast();
        assertEquals("4", L.getLast());
	}

	@Test
	void testRemoveIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertThrows(NullPointerException.class, ()->{L.removeIterator();});
		//test 2
		L.addFirst("A");
		L.positionIterator();
		L.addIterator("B");
		L.removeIterator();
		assertEquals("A",L.getIterator());
		//test 3
		L.addFirst("C");
		L.positionIterator();
		L.addIterator("D");
		L.removeIterator();
		assertEquals("C",L.getIterator());
				
	}

	@Test
	void testPositionIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		L.addFirst("A");
		L.positionIterator();
		assertEquals("A",L.getIterator());
		//test 2
		L.addFirst("B");
		L.positionIterator();
		assertEquals("B",L.getIterator());
		//test 3
		L.removeIterator();
		L.positionIterator();
		assertEquals("A",L.getIterator());
	}

	@Test
	void testAdvanceIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertThrows(NullPointerException.class, ()->{L.advanceIterator();});
		//test 2
		L.addFirst("A");
		L.addFirst("B");
		L.positionIterator();
		L.advanceIterator();
		assertEquals("A",L.getIterator());
		//test 3
		L.positionIterator();
		L.addFirst("C");
		L.advanceIterator();
		assertEquals("A",L.getIterator());
	}

	@Test
	void testReverseIterator() {
		//test 1
		LinkedList<String> L = new LinkedList<>();
		assertThrows(NullPointerException.class, ()->{L.reverseIterator();});
		//test 2
		L.addFirst("A");
		L.positionIterator();
		L.addFirst("B");
		L.reverseIterator();
		assertEquals("B",L.getIterator());
		//test 3 
		L.addFirst("C");
		L.addFirst("D");
		L.reverseIterator();
		assertEquals("C",L.getIterator());
				
	}

	@Test
	void testToString() {
		LinkedList<String> L = new LinkedList<>();
		//test 1
		assertEquals("\n", L.toString());
		//test 2
		 L.addFirst("1");
	     L.addFirst("5");
	     L.addFirst("4");
	     assertEquals("4 5 1 "+"\n", L.toString());
	     //test 3
	     L.removeLast();
	     L.removeLast();
	     assertEquals("4 "+"\n", L.toString());
		
	}

	@Test
	void testEqualsObject() {
		//test1
		LinkedList<String> List1 = new LinkedList<>(new String[] {"1", "2", "3"});
        LinkedList<String> List2 = new LinkedList<>(new String[] {"1", "2", "3", "4"});
        assertEquals(false,List1.equals(List2));
        //test 2
        List1.addLast("4");
        assertEquals(true,List1.equals(List2));
        //test 3
        List1.removeLast();
	    List2.removeLast();
	    assertEquals(true,List1.equals(List2));
	}

	@Test
    void testZipperLists() {
        LinkedList<String> nullList = new LinkedList<>();
        LinkedList<String> iList = new LinkedList<>();
        LinkedList<String> sList1 = new LinkedList<>(new String[] {"!", "?", "."});
        LinkedList<String> sList2 = new LinkedList<>(new String[] {"!", "?", ".", ","});
        
        assertEquals(sList1.zipperLists(nullList).toString(), sList1.toString());
        assertEquals(iList.zipperLists(sList1).toString(), sList1.toString());
        LinkedList<String> temp = sList1.zipperLists(sList2);
        assertEquals(sList1.toString(), "! ? . \n"); // error is here reading a new line 
        assertEquals(sList2.toString(), "! ? . , \n");
        assertEquals(temp.toString(), "! ! ? ? . . , \n");
        
        temp = sList2.zipperLists(sList1);
        assertEquals(sList1.toString(), "! ? . \n");
        assertEquals(sList2.toString(), "! ? . , \n");
        assertEquals(temp.toString(), "! ! ? ? . . , \n");
        
    }

    @Test
    void testIsReversible() {
        LinkedList<Integer> nums = new LinkedList<>();
        assertTrue(nums.isReversible());
        nums.addFirst(1);
        assertTrue(nums.isReversible());
        nums = new LinkedList<>(new Integer[] {1, 2, 3, 2, 1});
        assertTrue(nums.isReversible());
        nums.positionIterator();
        nums.advanceIterator();
        nums.addIterator(3);
        assertTrue(nums.isReversible());
        nums.advanceIterator();
        nums.addIterator(4);
        assertTrue(nums.isReversible());
        nums.addIterator(5);
        assertFalse(nums.isReversible());

        LinkedList<String> letters = new LinkedList<>(new String[] {"A", "B", "B", "A"});
        assertTrue(letters.isReversible());
        letters.addFirst(new String("D"));
        letters.addLast("D");
        assertTrue(letters.isReversible());
        letters.removeLast();
        assertFalse(letters.isReversible());
    }

}