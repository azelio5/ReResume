package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.ContactType;
import ru.anvar.webapp.model.Resume;
import ru.anvar.webapp.model.Section;
import ru.anvar.webapp.model.SectionType;

import java.io.*;
import java.util.Map;


public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file); //ARM -J7 - Auto Close
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(r);
            }
         catch (IOException e) {
            throw new WebAppException("Couldn't create file  " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) { // читаем в том же порядке что и пишем
        try {
            try (FileInputStream fis = new FileInputStream(file); //ARM -J7 - Auto Close
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                return (Resume)ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new WebAppException("Couldn't read file  " + file.getAbsolutePath(), e);
        }

    }

}
