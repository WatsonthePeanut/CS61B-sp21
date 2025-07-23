package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void testIntegerDeque() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(new IntegerComparator());
        for (int i = 0; i < 100000; ++i) {
            ad1.addFirst(i);
        }
        for (int i = 100001; i < 200000; ++i) {
            ad1.addLast(i);
        }
        assertEquals(199999, (int) ad1.max());
    }

    @Test
    public void testStringDeque() {
        MaxArrayDeque<String> ad1 = new MaxArrayDeque<>(new StringComparator());
        ad1.addFirst("Elyse");
        ad1.addLast("Sture");
        ad1.addFirst("Benjamin");
        ad1.addLast("Oski");
        ad1.addFirst("Cerebus");
        assertEquals("Sture", ad1.max());
    }

    @Test
    public void testNullDeque() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(new IntegerComparator());
        MaxArrayDeque<String> ad2 = new MaxArrayDeque<>(new StringComparator());
        assertEquals(null, ad1.max());
        assertEquals(null, ad2.max());
    }

    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }
}
