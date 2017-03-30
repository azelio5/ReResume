package ru.anvar.webapp;


import ru.anvar.webapp.model.Resume;

public class WebAppException extends RuntimeException {

    private Resume resume = null; // קעמ חא נוח‏לו

    private String uuid =  null; // קעמ חא uuid

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable e) {
        super(message, e);
    }

    public WebAppException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebAppException(String message, Resume resume, Throwable e) {
        super(message, e);
        this.resume = resume;
    }

    public WebAppException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
