package TP1.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import TP1.TDA.List.LinkedList.LinkedList;

public class LinkedListTest {

    private LinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void insertFrontTest(){
        list.insertFront(10);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
    }

    @Test
    public void insertOrderTest() {
        list.insertOrder(30);
        list.insertOrder(10);
        list.insertOrder(20);

        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
        assertEquals(Integer.valueOf(30), list.get(2));
    }

    @Test
    public void extractFrontTest() {
        list.insertFront(10);
        list.insertFront(20);
        
        assertEquals(Integer.valueOf(20), list.extractFront());
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.extractFront());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeTest() {
        list.insertFront(10);
        list.insertFront(20);
        list.insertFront(30);

        list.remove(20);

        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(30), list.get(0));
        assertEquals(Integer.valueOf(10), list.get(1));
        assertNull(list.get(2));

        list.remove(30);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));

        list.remove(10);
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        assertTrue(list.isEmpty());
        list.insertFront(10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void getTest() {
        list.insertFront(10);
        list.insertFront(20);
        list.insertFront(30);

        assertEquals(Integer.valueOf(30), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
        assertEquals(Integer.valueOf(10), list.get(2));
        assertNull(list.get(3)); // Fuera de rango
    }

    @Test
    public void sizeTest() {
        assertEquals(0, list.size());
        list.insertFront(10);
        assertEquals(1, list.size());
        list.insertFront(20);
        assertEquals(2, list.size());
    }

    @Test
    public void indexOfTest() {
        list.insertFront(10);
        list.insertFront(20);
        list.insertFront(30);

        assertEquals(0, list.indexOf(30));
        assertEquals(1, list.indexOf(20));
        assertEquals(2, list.indexOf(10));
        assertEquals(-1, list.indexOf(40)); // No existe
    }

    @Test
    public void toStringTest() {
        assertEquals("Lista vacia", list.toString());

        list.insertFront(10);
        list.insertFront(20);
        list.insertFront(30);

        assertEquals("30 20 10 ", list.toString());
    }
}
