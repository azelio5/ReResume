package storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.anvar.webapp.WebAppException;
import ru.anvar.webapp.model.ContactType;
import ru.anvar.webapp.model.Resume;
import ru.anvar.webapp.model.SectionType;
import ru.anvar.webapp.storage.IStorage;

import java.util.*;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

abstract public class AbstractStorageTest {
    protected IStorage storage;
    private Resume R1, R2, R3;

    @BeforeClass

    // Тоже что и "static {}" выше
    // этот блок выполняется один раз при загрузке класса в память
    // загрузке типа класса а не его экземплара
    // блок которые работает каждый раз при инициализации инстанса
    // выглядит как просто блок {} без слова static

    public static void beforeClass() {

    }

    @Before

    // перед каждым тестом чистим и записываем значения заново

    public void before() {

        R1 = new Resume("Полное имя1", "Локация1", "www-ленинрад-спб.ру");
        R1.addContact(ContactType.MAIL, "mailme.gmail.com");
        R1.addContact(ContactType.PHONE, "777-77-77");
        R1.addObjective("Objective1");
        R1.addMultiTextSection(SectionType.ACHIEVEMENT, "R1_Achievment1", "R1_Achievment2");
        R1.addMultiTextSection(SectionType.QUALIFICATION, "Java", "SQL");

//        R1.addOrganizationSection(SectionType.EXPIRIENCE,
//                new Organization("Organization11",null,
//                        new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1),Organization.Period.NOW,"position1", "content1"),
//                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY,"position2", "content2")),
//                new Link("Organization12","http://Organization12.ru"));



        R2 = new Resume("Полное имя2", "Локация2", "www.yandex.ua");
        R2.addContact(ContactType.MAIL, "mailme@yandex.com");
        R2.addContact(ContactType.SKYPE, "preil200");
        R2.addObjective("Objective2");
        R2.addMultiTextSection(SectionType.ACHIEVEMENT, "R2_Achievment1", "R2_Achievment2");
        R2.addMultiTextSection(SectionType.QUALIFICATION, "PHP", "MySQL");

        R3 = new Resume("Полное имя3", "Локация3", "www.google.ру");
        R3.addContact(ContactType.MAIL, "mailme@hotbox.com");
        R3.addContact(ContactType.PHONE, "222-55-11");
        R3.addObjective("Objective3");
        R3.addMultiTextSection(SectionType.ACHIEVEMENT, "R3_Achievment1", "R3_Achievment2");
        R3.addMultiTextSection(SectionType.QUALIFICATION, "Scala", "PostgreSQL");

        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);

    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        //for(int i = 0; )
    }

    @Test
    public void update() throws Exception {
        R2.setFullName("Updated №2");
        storage.update(R2);
        assertEquals(R2,storage.load(R2.getUuid()));
    }

    @Test
    public void load() throws Exception {
        assertEquals(R1, storage.load(R1.getUuid()));
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
        //Assert.assertEquals(null,storage.load(R1.getUuid()));
        //storage.load(R1.getUuid());
    }

    @Test(expected = WebAppException.class)
    public void deleteNotFound() throws Exception {
        storage.load("dummy");
    }

    @Test(expected = WebAppException.class)
    public void deleteMissed() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = WebAppException.class)
    public void savePresented() throws Exception {
        storage.save(R1);
    }

    @Test(expected = WebAppException.class)
    public void updateMissed() throws Exception{
        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);
    }

    @org.junit.Test
    public void getAllSorted() throws Exception {
//        Resume[]src = new Resume[]{R1, R2, R3};
//        Arrays.sort(src);
//        assertArrayEquals(src, storage.getAllSorted().toArray());

//         Тоже что выше но с использованием коллекции
//        List<Resume> list = Arrays.asList(R1,R2,R3);
//        Collections.sort(list);
//       assertEquals(list, storage.getAllSorted());

        List<Resume> list = Arrays.asList(R1, R2, R3);
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return 0;
            }
        });
        assertEquals(list, new ArrayList<>(storage.getAllSorted()));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}
