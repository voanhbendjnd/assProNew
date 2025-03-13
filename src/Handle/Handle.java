package handle;

import java.util.List;
import java.util.Optional;

public interface Handle<T> {
    public List<T> read(String file);

    public void writeFile(String fileName, List<T> list);

    public void addNew(String fileName, T t);

    public void deleteIt(String fileName, Optional<?> kei);
}
