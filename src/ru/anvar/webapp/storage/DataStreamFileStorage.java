package ru.anvar.webapp.storage;

import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.*;

import java.io.*;
import java.util.*;


public class DataStreamFileStorage extends FileStorage {

    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file); //ARM -J7 - Auto Close
             final DataOutputStream dos = new DataOutputStream(fos)) {

            writeString(dos, r.getFullName());
            writeString(dos, r.getLocation());
            writeString(dos, r.getHomePage());

            Map<ContactType, String> contacts = r.getContacts();


            Map<SectionType, Section> sections = r.getSections();


           writeCollection(dos, contacts.entrySet(), new ElementWriter<Map.Entry<ContactType, String>>() {
               @Override
               public void write(Map.Entry<ContactType, String> entry) throws IOException {
                    dos.writeInt(entry.getKey().ordinal());
                    writeString(dos, entry.getValue());
               }
           });


            dos.writeInt(sections.size()); // записываем число секций

            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                        break;
                    case QUALIFICATION:
                        writeCollection(dos, ((MultiTextSection) section).getValues(), new ElementWriter<String>() {
                            @Override
                            public void write(String s) throws IOException {
                                writeString(dos, s);
                            }
                        });
                        break;
                    case EDUCATION:
                        break;
                }
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file  " + file.getAbsolutePath(), r, e);
        }
    }



    protected Resume read(File file) { // читаем в том же порядке что и пишем
        Resume r = new Resume(file.getName());
        try {
            try (InputStream is = new FileInputStream(file); //ARM -J7 - Auto Close
                 DataInputStream dis = new DataInputStream(is)) {

                r.setFullName(readString(dis));
                r.setLocation(readString(dis));
                r.setHomePage(readString(dis));

                int contactSize = dis.readInt(); //считываем число контактов

                for (int i = 0; i < contactSize; i++) {
                    r.addContact(ContactType.ContactValues[dis.readInt()], readString(dis));
                }

                final int sectionSize = dis.readInt(); //считываем число секций

                for (int i = 0; i < sectionSize; i++) {
                    //   r.addSection(SectionType.SectionValues[dis.readInt()], dis.readUTF());// восстанавливаем в порядке как enum
                    SectionType sectionType = SectionType.valueOf(readString(dis));

                    switch (sectionType) {
                        case OBJECTIVE:
                            r.addObjective(readString(dis));
                            break;
                        case ACHIEVEMENT:
                            break;
                        case QUALIFICATION:
                            r.addSection(sectionType,
                                    new MultiTextSection(readList(dis, new ElementReader<String>() {
                                        @Override
                                        public String read() throws IOException {
                                            return DataStreamFileStorage.this.readString(dis);
                                        }
                                    })));

                            break;
                        case EDUCATION:
                            break;
                        case EXPERIENCE:
                            break;
                    }
                }

                r.setUuid(file.getName());
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file  " + file.getAbsolutePath(), e);
        }

        return r;
    }

    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

    private interface ElementWriter<T>{
        void write(T t) throws IOException;

    }

    private interface ElementReader<T>{
        T read() throws IOException;

    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

}
