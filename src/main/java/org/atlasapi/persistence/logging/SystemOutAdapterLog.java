package org.atlasapi.persistence.logging;

public class SystemOutAdapterLog implements AdapterLog {

    @Override
    public void record(AdapterLogEntry entry) {
        
        System.out.println("Adapter log entry: " + entry.severity());
        System.out.println(entry.timestamp());
        
        if (entry.description() != null) {
            System.out.println(entry.description());
        }
        
        if (entry.classNameOfSource() != null){
            System.out.println("Source: " + entry.classNameOfSource());
        }
        
        if (entry.uri() != null) {
            System.out.println("Uri: " + entry.uri()); 
        }
        
        if (entry.exceptionSummary() != null) {
            for (String trace : entry.exceptionSummary().traceAndMessage()) {
                System.out.println(trace);
            }
        }
    }

}
