import java.util.Iterator;

public class CustomArrayList<T> implements Iterable<T> {
    private Object[] data;
    private int size;

    public CustomArrayList() {
        data = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) resize();
        data[size++] = item;
    }

    public T get(int index) {
        if (index >= 0 && index < size) return (T) data[index];
        return null;
    }

    public void set(int index, T item) {
        if (index >= 0 && index < size) data[index] = item;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++) newData[i] = data[i];
        data = newData;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public T next() {
                return (T) data[current++];
            }
        };
    }
}
