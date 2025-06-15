package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (super.size() == 0) {
            return null;
        }
        T returnItem = get(0);
        for (int i = 1; i < super.size(); i++) {
            if (comparator.compare(returnItem, get(i)) < 0) {
                returnItem = get(i);
            }
        }
        return returnItem;
    }

    public T max(Comparator<T> c) {
        if (super.size() == 0) {
            return null;
        }
        T returnItem = get(0);
        for (int i = 1; i < super.size(); i++) {
            if (c.compare(returnItem, get(i)) < 0) {
                returnItem = get(i);
            }
        }
        return returnItem;
    }
}
