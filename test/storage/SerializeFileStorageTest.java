package storage;

import ru.anvar.webapp.storage.AbstractStorage;
import ru.anvar.webapp.storage.SerializeFileStorage;

public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage(AbstractStorage.FILE_STORAGE);

    }
}
