package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T data, StuffNode n, StuffNode l) {
            this.item = data;
            this.next = n;
            this.prev = l;
        }
    }

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T data) {
        StuffNode newNode = new StuffNode(data, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T data) {
        StuffNode newNode = new StuffNode(data, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        StuffNode current = sentinel.next;
        T x = current.item;
        current.next.prev = current.prev;
        sentinel.next = current.next;
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        StuffNode current = sentinel.prev;
        T x = current.item;
        current.prev.next = current.next;
        sentinel.prev = current.prev;
        return x;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        StuffNode current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(StuffNode s, int index) {
        if (index == 0) {
            return s.item;
        }
        return getRecursive(s.next, index - 1);
    }
}
