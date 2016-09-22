package com.equiniti.qa_report.objectmapper;


public class CreatorFactory {

    private LocalDateCreator localDateCreator;
    
    private DateCreator dateCreator;
    
    private DateTimeCreator dateTimeCreator;
    
    public LocalDateCreator createLocalDateCreator() {
        if (localDateCreator == null) {
            localDateCreator = new LocalDateCreator();
        }
        return localDateCreator;
    }
    
    public DateCreator createDateCreator() {
        if (dateCreator == null) {
        	dateCreator = new DateCreator();
        }
        return dateCreator;
    }
    
    public DateTimeCreator createDateTimeCreator() {
        if (dateTimeCreator == null) {
        	dateTimeCreator = new DateTimeCreator();
        }
        return dateTimeCreator;
    }
    
}
