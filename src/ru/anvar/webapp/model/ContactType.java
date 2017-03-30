package ru.anvar.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable{
    PHONE("Телефон"),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний"),
    SKYPE("Skype"),
    MAIL("Почта");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ContactType[] ContactValues = ContactType.values();

}
