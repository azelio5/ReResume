package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
//import java.util.logging.Level;


//Для массива context это индекс

public class ArrayStorage extends AbstractStorage<Integer> {

    private static final int LIMIT = 100;

    int size = 0; // размер массива
    private Resume[] array = new Resume[LIMIT];


    @Override
    public void doClear() {

        Arrays.fill(array, null);

        size = 0;// обнуляем размер массива

    }


    @Override
    protected boolean exist(Integer idx) {
        return idx != -1;
    }


    @Override
    protected void doUpdate(Integer idx, Resume r) {

//        if (idx == -1) throw new WebAppException("Resume " + r.getUuid() + " is not exist");

        array[idx] = r;

    }


    @Override
    protected Resume doLoad(Integer idx) {

//        if (idx == -1) throw new WebAppException("Resume " + uuid + " is not exist");

        return array[idx];
    }

    @Override
    protected void doDelete(Integer idx) {
        //From ArrayList remove method


        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx + 1, array, idx,
                    numMoved);
        array[--size] = null; // clear to let GC do its work

    }


    @Override
    protected void doSave(Integer context, Resume r) {
        array[size++] = r;
    }

    @Override
    public Collection<Resume> getAllSorted() {

        //todo comparator

        logger.info("getAllSorted");
        Arrays.sort(array, 0, size);
        return Arrays.asList(Arrays.copyOf(array, size));
    }

    @Override
    protected List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    protected Integer getContext(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }

        return -1;
    }


}
