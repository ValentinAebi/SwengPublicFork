package sweng;

import java.util.ArrayList;
import java.util.List;

public class CachedCSVReader {
    private final CSVReader underlyingReader;
    private final Cache<Integer, Student> cache;

    public CachedCSVReader(CSVReader underlyingReader, Cache<Integer, Student> cache) {
        this.underlyingReader = underlyingReader;
        this.cache = cache;
    }

    public List<Student> read(int n){
        List<Student> fromCache = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            if (cache.contains(i)) {
                fromCache.add(cache.get(i));
            }
            else {
                List<Student> readLs = underlyingReader.read(n);
                int j = 0;
                for (Student s : readLs) {
                    cache.put(j, s);
                    ++j;
                }
            }
        }
        return fromCache;
    }

}
