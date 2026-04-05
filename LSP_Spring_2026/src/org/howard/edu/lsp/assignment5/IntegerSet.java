package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IntegerSet represents a mathematical set of integers.
 */
public class IntegerSet {

    private List<Integer> set = new ArrayList<>();

    /**
     * Clears the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the length of the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * Checks if two sets are equal.
     */
    public boolean equals(IntegerSet b) {
        if (b == null) return false;

        List<Integer> temp1 = new ArrayList<>(this.set);
        List<Integer> temp2 = new ArrayList<>(b.set);

        Collections.sort(temp1);
        Collections.sort(temp2);

        return temp1.equals(temp2);
    }

    /**
     * Checks if value exists in set.
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns largest value.
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns smallest value.
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds item (no duplicates allowed).
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes item.
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Union operation.
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);

        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Intersection operation.
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (int item : this.set) {
            if (intSetb.set.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Difference (this - b).
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (int item : this.set) {
            if (!intSetb.set.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Complement (b - this).
     */
    public IntegerSet complement(IntegerSet intSetb) {
        return intSetb.diff(this);
    }

    /**
     * Checks if set is empty.
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns string representation in sorted order.
     */
    @Override
    public String toString() {
        List<Integer> temp = new ArrayList<>(set);
        Collections.sort(temp);
        return temp.toString();
    }
}