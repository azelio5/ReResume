package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.Resume;

import java.util.Collection;

public interface IStorage {
    void clear();
    void save(Resume r) throws WebAppException;
    void update(Resume r)throws WebAppException;
    Resume load(String uuid);
    void delete(String uuid);

    Collection<Resume> getAllSorted();
    int size();
}
