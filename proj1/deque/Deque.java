package deque;

public interface Deque<T> {
    void addFirst(T data);

    void addLast(T data);

    boolean isEmpty() ;

    int size() ;

    void printDeque() ;

    T removeFirst() ;

    T removeLast();

    T get(int index) ;

}
