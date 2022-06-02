package storage.impl;

import model.Record;
import storage.ReceivingData;

import java.util.*;

public class LocalStorage implements ReceivingData {

    private static Map<String, Record> records;

    static {
        records = new HashMap<>();
    }
    @Override
    public Collection<Record> getRecords() {
        return records.values();
    }

    @Override
    public void add(Record record) {
        records.put(record.getResource(), record);
    }

    @Override
    public Record getBySource(String resource) {
        return records.get(resource);
    }

    @Override
    public boolean remove(String resource) {
        return records.remove(resource) != null;
    }

    @Override
    public void update(Record updatedRecord) {
        records.replace(updatedRecord.getResource(), updatedRecord);
    }

    @Override
    public boolean checkResource(String resource) {
        return records.containsKey(resource);
    }
}
