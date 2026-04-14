package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet.
 */
public class IntegerSetTest {

    @Test
    public void testClearNormalAndEdge() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());

        // Edge case: clearing an already empty set
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    public void testLengthNormalAndEdge() {
        IntegerSet set = new IntegerSet();
        assertEquals(0, set.length()); // edge: empty set

        set.add(10);
        set.add(20);
        assertEquals(2, set.length()); // normal
    }

    @Test
    public void testEqualsSameElementsDifferentOrderAndMismatch() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        IntegerSet set3 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(1);
        set2.add(2);

        set3.add(1);
        set3.add(2);
        set3.add(4);

        assertTrue(set1.equals(set2));   // same elements, different order
        assertFalse(set1.equals(set3));  // mismatch
        assertFalse(set1.equals(null));  // edge: null
    }

    @Test
    public void testContainsPresentAndAbsent() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(10);

        assertTrue(set.contains(5));   // normal
        assertFalse(set.contains(99)); // edge: absent
    }

    @Test
    public void testLargestNormalAndException() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        set.add(2);
        set.add(10);

        assertEquals(10, set.largest()); // normal

        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals(42, single.largest()); // single element

        IntegerSet empty = new IntegerSet();
        assertThrows(RuntimeException.class, empty::largest); // edge: empty
    }

    @Test
    public void testSmallestNormalAndException() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        set.add(2);
        set.add(10);

        assertEquals(2, set.smallest()); // normal

        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals(42, single.smallest()); // single element

        IntegerSet empty = new IntegerSet();
        assertThrows(RuntimeException.class, empty::smallest); // edge: empty
    }

    @Test
    public void testAddNormalAndDuplicate() {
        IntegerSet set = new IntegerSet();
        set.add(8);
        set.add(3);

        assertEquals(2, set.length()); // normal add

        set.add(8); // duplicate
        assertEquals(2, set.length()); // should not increase
        assertEquals("[3, 8]", set.toString());
    }

    @Test
    public void testRemoveNormalAndMissing() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        set.remove(2); // normal remove
        assertEquals("[1, 3]", set.toString());

        set.remove(99); // edge: value not present
        assertEquals("[1, 3]", set.toString());
    }

    @Test
    public void testUnionNormalAndWithEmptySet() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        IntegerSet empty = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(2);
        set2.add(3);

        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2, 3]", result.toString()); // normal

        IntegerSet resultWithEmpty = set1.union(empty);
        assertEquals("[1, 2]", resultWithEmpty.toString()); // edge: union with empty
    }

    @Test
    public void testIntersectNormalAndNoCommonElements() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        IntegerSet set3 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);

        set3.add(7);
        set3.add(8);

        IntegerSet result = set1.intersect(set2);
        assertEquals("[2, 3]", result.toString()); // normal

        IntegerSet noCommon = set1.intersect(set3);
        assertEquals("[]", noCommon.toString()); // edge: no overlap
    }

    @Test
    public void testDiffNormalAndIdenticalSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        IntegerSet set3 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(4);

        set3.add(1);
        set3.add(2);
        set3.add(3);

        IntegerSet result = set1.diff(set2);
        assertEquals("[1, 3]", result.toString()); // normal

        IntegerSet identicalResult = set1.diff(set3);
        assertEquals("[]", identicalResult.toString()); // edge: identical sets
    }

    @Test
    public void testComplementNormalAndDisjointSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        IntegerSet set3 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(2);
        set2.add(3);
        set2.add(4);

        set3.add(7);
        set3.add(8);

        IntegerSet result = set1.complement(set2); // set2 - set1
        assertEquals("[3, 4]", result.toString()); // normal

        IntegerSet disjointResult = set1.complement(set3); // set3 - set1
        assertEquals("[7, 8]", disjointResult.toString()); // edge: disjoint
    }

    @Test
    public void testIsEmptyEmptyAndNonEmpty() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty()); // empty

        set.add(100);
        assertFalse(set.isEmpty()); // non-empty
    }

    @Test
    public void testToStringNormalAndEmpty() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString()); // edge: empty set

        set.add(3);
        set.add(1);
        set.add(2);
        assertEquals("[1, 2, 3]", set.toString()); // normal, sorted format
    }
}