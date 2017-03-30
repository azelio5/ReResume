package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.ContactType;
import ru.anvar.webapp.model.Resume;
import ru.anvar.webapp.model.Section;
import ru.anvar.webapp.model.SectionType;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


public abstract class FileStorage extends AbstractStorage<File> {

    private File dir;

    public FileStorage() {
    }

    public FileStorage(String path) {  ///каталог
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException(" " + path + " - Is not directory or not writable!");
        }
    }


    @Override
    protected void doClear() {
        File[] files = dir.listFiles(); // все файлы из каталога
        if (files == null) return;
        for (File file : files) {
            doDelete(file);
        }

    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected boolean exist(File fileName) {
        return fileName.exists();
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);
    }

    @Override
    protected Resume doLoad(File file) {
        return read(file);

    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppException("File " + file.getAbsolutePath() + "Can not be deleted");
        }

    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = dir.listFiles();
        if (files == null) return Collections.emptyList();
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) list.add(read(file));
        return list;
    }

    @Override
    public int size() {
        String[] list = dir.list();
        if (list == null) throw new WebAppException("Couldn't read directory " + dir.getAbsolutePath());
        return list.length;
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();


        } catch (IOException e) {
            throw new WebAppException("Couldn't create file  " + file.getAbsolutePath() + "Can not be deleted", r, e);
        }
        write(file, r);

    }

    protected abstract void write(File file, Resume r);

    protected abstract Resume read(File file);
}


