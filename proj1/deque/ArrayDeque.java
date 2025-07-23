package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private int mod(int index) {
        int m = items.length;
        return (index % m + m) % m; // 利用求模保证下标在[0, items.length)区间内且非负
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            a[i] = get(i);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = mod(nextFirst - 1); // nextFirst 向后移动
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = mod(nextLast + 1); // nextLast 向前移动
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        nextFirst = mod(nextFirst + 1); // nextFirst 向前移动
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        nextLast = mod(nextLast - 1);   // nextLast 向后移动
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return removedItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(get(i));
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // 处理逻辑下标与物理下标的对应
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[mod(nextFirst + 1 + index)];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> otherAD = (ArrayDeque<T>) o;
            if (size != otherAD.size) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                T a = get(i);
                T b = otherAD.get(i);
                if (a == null) {        // 确保处理 null 值
                    if (b != null) {
                        return false;
                    }
                } else if (!a.equals(b)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T returnVal = get(index);
            index += 1;
            return returnVal;
        }
    }
}