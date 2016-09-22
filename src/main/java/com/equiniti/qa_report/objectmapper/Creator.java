package com.equiniti.qa_report.objectmapper;

public interface Creator<T> {

    public T create(Object source);
    
}
