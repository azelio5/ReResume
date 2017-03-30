package ru.anvar.webapp.model;

import java.io.Serializable;
import java.util.*;

public class Resume implements Serializable, Comparable<Resume>{//Comparable<Resume> {

    static final long serialVersionUID = 1L;

    private String uuid; //уникальный строковой идентификатор

    private String fullName;
    private String location;
    private String homePage;

//    List<Contact> contacts = new LinkedList<>();

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);



    public  static  final Resume EMPTY;

    static{
        EMPTY = new Resume();
    }

    public Resume() {
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public void addSection(SectionType type, Section section){
        sections.put(type, section);
    }

    public void addContact(ContactType type,String value){
        contacts.put(type, value);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

//    public List<Contact> getContacts() {
//        return contacts;
//    }


    public void addObjective(String value){
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }

    public void addMultiTextSection(SectionType type, String... values){
        addSection(type, new MultiTextSection(values));
    }

    public String getContact(ContactType type){
        return contacts.get(type);
    }

    public Section getSection(SectionType type){
        return sections.get(type);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return Objects.equals(this.uuid, other.uuid)
                && Objects.equals(this.fullName, other.fullName)
                && Objects.equals(this.location, other.location)
                && Objects.equals(this.homePage, other.homePage)
                && Objects.equals(this.contacts, other.contacts)
                && Objects.equals(this.sections, other.sections);
    }

@Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }



    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", homePage='" + homePage + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

//    public void setContacts(List<Contact> contacts) {
//        this.contacts = contacts;
//    }


    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

//    public void setSections(List<Section> sections) {
//        this.sections = sections;
//    }

//    @Override
//    public int compareTo(Resume o) {
//        return fullName.compareTo(o.fullName);
//    }

//    private String getEmail(List<Contact> list) {
//        for (Contact c : list) {
//            if (c.getType() == ContactType.MAIL) {
//                c.getValue();
//            }
//
//        }
//        return null;
//    }


    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    }
