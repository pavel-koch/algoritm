package org.example;

import java.util.Arrays;

public class IntegerArrayList implements IntegerList {

    private Integer[] table;
    private int size = 0;
    private static int currentCapacity;
    private final static int DEFAULT_CAPACITY = 10;

    public IntegerArrayList(int capacity) {
        this.table = new Integer[capacity];
        currentCapacity = capacity;
    }

    public IntegerArrayList() {
        this.table = new Integer[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public Integer add(Integer item) {
        checkNull(item);

        validateSize();

        table[size++] = item;

        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkNull(item);

        validateSize();
        validateIndex(index);

        System.arraycopy(table, index, table, index + 1, table.length + 1);

        table[index] = item;
        size++;

        return null;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNull(item);
        validateIndex(index);
        Integer oldItem = table[index];
        table[index] = item;

        return oldItem;
    }

    @Override
    public Integer remove(Integer item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (table[i].equals(item)) {
                Integer oldItem = table[i];
                table[i] = null;
                size--;
                System.arraycopy(table, i, table, i - 1, table.length + 1);
                return oldItem;
            }

        }
        throw new RuntimeException();//todo
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer oldItem = table[index];
        table[index] = null;
        size--;
        System.arraycopy(table, index, table, index - 1, table.length + 1);
        return oldItem;
    }

    @Override
    public boolean contains(Integer item) {
        checkNull(item);
        Integer[] copy = toArray();

        return binarySearch(table, item) != 1;
    }

    @Override
    public int indexOf(Integer item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (table[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (table[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return table[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        Arrays.equals(table, otherList.toArray());
        return false;
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
    public void clear() {
        table = new Integer[currentCapacity];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(table, size);
    }

    public void checkNull(Integer item) {
        if (item == null) {
            throw new NullItemException(); //todo
        }
    }

    public void validateSize() {
        if (size == table.length) {
            throw new TableIsFullExeption();
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexExeption(); //todo
        }

    }

    private static void sort(Integer[] table) {
        for (int i = 1; i < table.length; i++) {
            int key = table[i];
            int j = i - 1;
            while (j >= 0 && table[j] > key) {
                table[j + 1] = table[j];
                j--;
            }
            table[j + 1] = key;
        }
    }

    private static int binarySearch(Integer[] table, Integer element) {
        int min = 0;
        int max = table.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.compareTo(table[mid]) > 0) {
                return mid;
            }

            if (element < table[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }
}
