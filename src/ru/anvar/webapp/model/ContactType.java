package ru.anvar.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable{
    PHONE("�������"),
    MOBILE("���������"),
    HOME_PHONE("��������"),
    SKYPE("Skype"),
    MAIL("�����");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ContactType[] ContactValues = ContactType.values();

}
