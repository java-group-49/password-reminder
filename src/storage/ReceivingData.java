package storage;

import model.Record;

import java.util.Collection;

public interface ReceivingData {
    Collection<Record> getRecords();
    void add(Record record);
    Record getBySource(String resource);
    boolean remove(String resource);
    void update(Record updatedRecord);
    boolean checkResource(String resource);
}
