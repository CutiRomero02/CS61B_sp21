package bstmap;


public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        private int size;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
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
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
