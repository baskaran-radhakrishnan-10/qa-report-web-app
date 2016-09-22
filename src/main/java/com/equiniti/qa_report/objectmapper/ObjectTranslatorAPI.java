package com.equiniti.qa_report.objectmapper;

import java.util.List;

public interface ObjectTranslatorAPI {

    public Object translateObject(Object source,Object destination,String mapperId);

    public Object translateObjectByClass(Object source,Class<?> destinationClass,String mapperId);
    
    public <T, U> List<U> listConverter(final List<T> source, final Class<U> destType,String mapperId);

}
