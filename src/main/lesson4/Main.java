package main.lesson4;

import ru.anvar.webapp.model.Organization;
import ru.anvar.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        Resume R1, R2, R3;
//
//        R1 = new Resume("Anvar", "Baku");
//        R1.addContact(new Contact(ContactType.MOBILE, "11111"));
//        R1.addContact(new Contact(ContactType.MAIL, "a.alakbarov1@gmail.com"));

//        System.out.println(R1);

        new Organization.OrganizationPeriod();

        Map<String, Resume> map = new HashMap<>();
        map.put("uuid", new Resume("uuid","",""));

//        for(String key: map.keySet()){
//            System.out.println(key + " -> " + map.get(key));
//        }

        for(Map.Entry<String , Resume> entry: map.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


    }
}
