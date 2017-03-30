package main.lesson5;

import ru.anvar.webapp.model.ContactType;
import ru.anvar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Comparator<Resume> comparator = (o1, o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return 0;
        };
    //    System.out.println(comparator);


        Resume R1 = new Resume("Anvar", "Baku");
        R1.addContact(ContactType.MOBILE, "11111");
        R1.addContact(ContactType.MAIL, "a.alakbarov1@gmail.com");

        Resume R2 = new Resume("Lyu", "Moscow");
        R2.addContact(ContactType.MOBILE, "22222");
        R2.addContact(ContactType.MAIL, "a.alakbarov2@gmail.com");

        Resume R3 = new Resume("Kri", "Yerevan");
        R3.addContact(ContactType.MOBILE, "33333");
        R3.addContact(ContactType.MAIL, "a.alakbarov3@gmail.com");

        List<Resume> resumes = Arrays.asList(R1,R2, R3);

        print(resumes);

}

    private static<T> void print(List<T> list) {
        list.forEach(r -> System.out.println(r.toString()));
    }
}


