package main.lesson3;

//import ru.anvar.webapp.model.Contact;
import ru.anvar.webapp.model.ContactType;
import ru.anvar.webapp.model.Link;
import ru.anvar.webapp.model.Resume;

import java.lang.reflect.Field;

/**
 * Created by anvar on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        //Contact contact = new Contact(ContactType.PHONE, "89268008818");

        // System.out.println(contact);


        Field f = Link.class.getDeclaredField("url");

        f.setAccessible(true);


        Link l = new Link("Gazprom","http://www.gazprom.ru");

        System.out.println(f.get(l));

        System.out.println(l instanceof Link);
        System.out.println(l.getClass().isInstance(Link.class));
        System.out.println(Link.class.isInstance(l));



    }
}
