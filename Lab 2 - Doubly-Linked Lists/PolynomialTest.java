/**
 * PolynomialTest.java
 * @author Jennifer Parrish
 * ADD TO THESE TESTS
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class PolynomialTest {
    private Polynomial p = new Polynomial();
    private Polynomial p1 = new Polynomial();
    
    private void setUp() {
        p.addTerm(3, 5);
        p.addTerm(2, 5);
        p.addTerm(1, 5);
        p.addTerm(0, 5);
        
        p1.addTerm(2, 3.0);
        p1.addTerm(1, 0.0);
        p1.addTerm(0, 12.0);
    }
    @Test
    void testCopyConstructor() {
        setUp();
        Polynomial copyP = new Polynomial(p);
        Polynomial copyP1 = new Polynomial(p1);
        Polynomial copyNull = new Polynomial(null);
        assertEquals("5.0x3 + 5.0x2 + 5.0x + 5.0\n", copyP.toString());
        assertEquals("3.0x2 + 12.0\n", copyP1.toString());
        assertEquals("\n", copyNull.toString());
        
        //testing for deep copy
        copyP1.updateTerm(2, 10.0);
        assertEquals("3.0x2 + 12.0\n", p1.toString());
        assertEquals("10.0x2 + 12.0\n", copyP1.toString());
    }

    @Test
    void testAddTerm() {
        setUp();
        assertEquals("5.0x3 + 5.0x2 + 5.0x + 5.0\n", p.toString());
        assertEquals("3.0x2 + 12.0\n", p1.toString());
    }
    
    @Test 
    void testUpdateTerm() {
        setUp();
        p.updateTerm(3, 2.0);
        assertEquals("2.0x3 + 5.0x2 + 5.0x + 5.0\n", p.toString());
        p.updateTerm(1, 4.0);
        assertEquals("2.0x3 + 5.0x2 + 4.0x + 5.0\n", p.toString());
        p.updateTerm(0, 10.0);
        assertEquals("2.0x3 + 5.0x2 + 4.0x + 10.0\n", p.toString());
        
        assertThrows(IllegalArgumentException.class, ()->{p.updateTerm(-1, 3);});
        assertThrows(IllegalArgumentException.class, ()->{p.updateTerm(4, 3);});
    }
    
    @Test
    void testEvaluate() {
        setUp();
        
        double result = p.evaluate(3.0);
        assertEquals(200, result);
        
        result = p1.evaluate(4);
        assertEquals(60, result);
    }

    @Test
    void testAdd() {
        setUp();
        //5.0x3 + 5.0x2 + 5.0x + 5.0
        //3.0x2 + 12.0
        Polynomial sum = p.add(p1);
        assertEquals("5.0x3 + 8.0x2 + 5.0x + 17.0\n", sum.toString());
        
        Polynomial sum2 = p1.add(p);
        assertEquals("5.0x3 + 8.0x2 + 5.0x + 17.0\n", sum2.toString());
    }

    @Test
    void testSubtract() {
        setUp();
        //5.0x3 + 5.0x2 + 5.0x + 5.0
        //3.0x2 + 12.0
        Polynomial difference = p.subtract(p1);
        assertEquals("5.0x3 + 2.0x2 + 5.0x + -7.0\n", difference.toString());
        //verify p1 is unchanged after calling subtract
        assertEquals("3.0x2 + 12.0\n", p1.toString());
        difference = p1.subtract(p);
        assertEquals("-5.0x3 + -2.0x2 + -5.0x + 7.0\n", difference.toString());
    }

    @Test
    void testToString() {
        setUp();
        assertEquals("5.0x3 + 5.0x2 + 5.0x + 5.0\n", p.toString());
        assertEquals("3.0x2 + 12.0\n", p1.toString());
        Polynomial p2 = new Polynomial();
        assertEquals("\n", p2.toString());
    }

}
