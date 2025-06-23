package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    int size;
    double loadFactor;
    int tableSize;


    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        tableSize = initialSize;
        size = 0;
        loadFactor = maxLoad;
    }


    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        Node node = new Node(key, value);
        return node;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] b = new Collection[tableSize];
        for(int i = 0; i < tableSize; i++) {
            b[i] = createBucket();
        }
        return b;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private void resize(int newSize) {
        Collection<Node>[] temp = this.buckets;
        this.buckets = createTable(newSize);
        for (Collection<Node> b : temp) {
            for (Node n : b) {
                putNoCheck(n);
            }
        }
    }

    private void putNoCheck(Node node) {
        int hash = node.key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        bucket.add(node);
    }


    @Override
    public void clear() {
        buckets = createTable(tableSize);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node(key, value));
        size += 1;
        double rate = ((double) buckets.length) / size;
        if (rate < loadFactor) {
            resize(buckets.length * 2);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        double rate = ((double) buckets.length) / size;
        if (rate > loadFactor) {
            resize(buckets.length / 2 + 1);
        }
        int hash = key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                V value = node.value;
                bucket.remove(node);
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        double rate = ((double) buckets.length) / size;
        if (rate > loadFactor) {
            resize(buckets.length / 2 + 1);
        }
        int hash = key.hashCode();
        int bucketIndex = Math.floorMod(hash, buckets.length);
        Collection<Node> bucket = buckets[bucketIndex];
        for (Node node : bucket) {
            if (node.key.equals(key) && node.value.equals(value)) {
                bucket.remove(node);
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }

}
