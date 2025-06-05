package deque;

public class ArrayDeque<T> implements Deque<T> {
    T[] item;
    int size;
    int front;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
        front = 0;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            temp[i] = item[(front + i) % item.length];
        }
        item = temp;
        front = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        try {
            item[front - 1] = x;
            front -= 1;
        } catch (IndexOutOfBoundsException e) {
            item[item.length - 1] = x;
            front = item.length - 1;
        } finally {
            size++;
        }
    }

    @Override
    public void addLast(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        item[(front + size) % item.length] = x;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = item[front];
        item[front] = null;
        front = (front + 1) % item.length;
        size--;
        double usageRatio = (double) size / item.length;
        if (usageRatio < 0.25) {
            resize(item.length / 2);
        }
        return res;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = item[(front + size - 1) % item.length];
        item[(front + size - 1) % item.length] = null;
        size--;
        double usageRatio = (double) size / item.length;
        if (usageRatio < 0.25) {
            resize(item.length / 2);
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return item[(front + index) % item.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }


}
