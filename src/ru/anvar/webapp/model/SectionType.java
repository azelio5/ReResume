package ru.anvar.webapp.model;

/**
 * Created by anvar on 12.02.2017.
 */
public enum SectionType {
    OBJECTIVE("�������"),
    ACHIEVEMENT("����������"),
    QUALIFICATION("������������"),
    EXPERIENCE("����"),
    EDUCATION("�����������");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static SectionType[] SectionValues =  SectionType.values();
}
