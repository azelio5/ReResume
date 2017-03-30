package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.Resume;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

abstract public class AbstractStorage<C> implements IStorage {
    public static final String FILE_STORAGE = "C:\\java\\reKislin\\file_storage";

    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Delete all resumes");
        doClear();
    }

    protected abstract void doClear();

    protected abstract C getContext(String uuid);

    protected abstract boolean exist(C context);

    @Override
    public void update(Resume r) {
        logger.info("Update resume with " + r.getUuid());
        C context = getContext(r);
        if (!exist(context)) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        }
        doUpdate(context, r);
    }

    protected abstract void doUpdate(C context, Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid = " + uuid);
        C context = getContext(uuid);
        if (!exist(context)) {
            throw new WebAppException("Resume " + uuid + "not exist");
        }
        return doLoad(context);
    }

    protected abstract Resume doLoad(C context);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid = " + uuid);
        C context = getContext(uuid);
        if (!exist(context)) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        doDelete(context);
    }

    protected abstract void doDelete(C context);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if (cmp != 0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
            }
        });

        return list;
    }


    protected abstract List<Resume> doGetAll();

    public abstract int size();

    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid = " + r.getUuid());
        C context = getContext(r);
        if (exist(context)) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        }
        doSave(context, r);
    }

    protected abstract void doSave(C context, Resume r);

    private C getContext(Resume r) {
        return getContext(r.getUuid());
    }
}