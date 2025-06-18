package bstmap;

import java.util.*;

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
        if (key == null) {
            throw new IllegalArgumentException("Cannot input null");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode node, K key) {
        if (node == null) {
            return false;
        }
        else if (node.key.equals(key)) {
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
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        else if (key.equals(node.key)) {
            node.value = value;
        }
        else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        size -= 1;
        V returnValue = get(key);
        root = remove(root, key);
        return returnValue;
    }

    private BSTNode remove(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        else if (key.equals(node.key)) {
            if (node.left == null) {
                if (node.right == null) {
                    return null;
                }
                else {
                    return node.right;
                }
            }
            else {
                if (node.right == null) {
                    return node.left;
                }
                else {
                    BSTNode predecessor = getPredecessor(node);
                    node.key = predecessor.key;
                    node.value = predecessor.value;
                    node.left = remove(node.left, predecessor.key);
                }
            }
        }
        else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }
        else {
            node.right = remove(node.right, key);
        }
        return node;
    }

    private BSTNode getPredecessor(BSTNode node) {
        BSTNode predecessor = node.left;
        while (predecessor.right != null) {
            predecessor = predecessor.right;
        }
        return predecessor;
    }

    @Override
    public V remove(K key, V value) {
        size -= 1;
        V returnValue = get(key);
        if (value.equals(returnValue)) {
            remove(key);
            return returnValue;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        addSet(root, set);
        return set;
    }

    private void addSet(BSTNode node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        addSet(node.left, set);
        addSet(node.right, set);
    }

    public void printInOrder() {
        List<V> list = orderedList(root);
        System.out.println(list);
    }

    private List<V> orderedList(BSTNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<V> left = orderedList(node.left);
        List<V> right = orderedList(node.right);
        left.add(node.value);
        left.addAll(right);
        return left;
    }
}
