package sweng;

import java.util.HashMap;
import java.util.Map;

public final class CSVReaderCache implements Cache<Integer, Student> {
    private final Map<Integer, Student> cachedValues = new HashMap<>();

    @Override
    public boolean contains(Integer key) {
        return cachedValues.containsKey(key);
    }

    @Override
    public void put(Integer key, Student value) {
        cachedValues.put(key, value);
    }

    @Override
    public Student get(Integer key) {
        return cachedValues.get(key);
    }

    @Override
    public void clear() {
        cachedValues.clear();
    }
}
