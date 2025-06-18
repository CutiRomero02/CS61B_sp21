package bstmap;


import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public K next() {
            throw new UnsupportedOperationException();
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode node, K key) {
        if (node == null) {
            return false;
        }
        else if (key.equals(node.key)) {
            return true;
        }
        else if (key.compareTo(node.key) < 0) {
            return containsKey(node.left, key);
        }
        else {
            return containsKey(node.right, key);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        else if (key.equals(node.key)) {
            return node.value;
        }
        else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        else {
            return get(node.right, key);
        }
    }

    @Override
    public void put(K key, V value) {
        size += 1;
        put(root, key, value);
    }

    private void put (BSTNode node, K key, V value) {
        if (node == null) {
            node = new BSTNode(key, value);
        }
        else if (key.equals(node.key)) {
            node.value = value;
        }
        else if (key.compareTo(node.key) < 0) {
            put(node.left, key, value);
        }
        else {
            put(node.right, key, value);
        }
    }

    @Override
    public V remove(K key) {
        size -= 1;
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        size -= 1;
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
}
