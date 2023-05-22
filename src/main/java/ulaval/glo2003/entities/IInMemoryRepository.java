package ulaval.glo2003.entities;

import java.util.List;
import java.util.UUID;

public interface IInMemoryRepository<T> {
    T get(UUID id);

    Boolean exists(UUID id);

    void save(T entity);

    void remove(UUID entityId);

    List<T> getAll();
}
