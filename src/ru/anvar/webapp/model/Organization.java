package ru.anvar.webapp.model;

import java.util.Date;
import java.util.List;

public class Organization {

    private Link link;
    private List<OrganizationPeriod> periods;

    public Organization() {
    }

    public Organization(Link link, List<OrganizationPeriod> periods) {
        this.link = link;
        this.periods = periods;
    }

    public static class OrganizationPeriod {
        private Date startDate;
        private Date entDate;
        private String position;
        private String content;

        public OrganizationPeriod() {
        }

        public OrganizationPeriod(Date startDate, Date entDate, String position, String content) {
            this.startDate = startDate;
            this.entDate = entDate;
            this.position = position;
            this.content = content;
        }
    }
}
