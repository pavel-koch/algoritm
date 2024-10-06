package org.example;

import java.util.Arrays;

public class StringArrayList implements StringList {

    private String[] table;
    private int size = 0;
    private final static int currentCapacity;
    private final static int DEFAULT_CAPACITY = 10;

    public StringArrayList(int capacity) {
        this.table = new String[capacity];
        currentCapacity = capacity;
    }

    public StringArrayList() {
        this.table = new String[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public String add(String item) {
        checkNull(item);

        validateSize();

        table[size++] = item;

        return item;
    }

    @Override
    public String add(int index, String item) {
        checkNull(item);

        validateSize();
        validateIndex(index);

        System.arraycopy(table, index, table, index + 1, table.length + 1);

        table[index] = item;
        size++;

        return null;
    }

    @Override
    public String set(int index, String item) {
        checkNull(item);
        validateIndex(index);
        String oldItem = table[index];
        table[index] = item;

        return oldItem;
    }

    @Override
    public String remove(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (table[i].equals(item)) {
                String oldItem = table[i];
                table[i] = null;
                size--;
                System.arraycopy(table, i, table, i - 1, table.length + 1);
                return oldItem;
            }

        }
        throw new RuntimeException();//todo
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String oldItem = table[index];
        table[index] = null;
        size--;
        System.arraycopy(table, index, table, index - 1, table.length + 1);
        return oldItem;
    }

    @Override
    public boolean contains(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (table[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (table[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (table[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return table[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        table = new String[currentCapacity];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(table, size);
    }

    public void checkNull(String item) {
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
}
