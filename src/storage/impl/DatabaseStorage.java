package storage.impl;

import dao.DbRequests;
import model.Record;
import storage.ReceivingData;

import java.util.Collection;

public class DatabaseStorage implements ReceivingData {
    @Override
    public Collection<Record> getRecords() {
        return DbRequests.getAllRecords();
    }

    @Override
    public void add(Record record) {
        DbRequests.save(record);
    }

    @Override
    public Record getBySource(String resource) {
        return DbRequests.getByResource(resource);
    }

    @Override
    public boolean remove(String resource) {
        return DbRequests.remove(resource);
    }

    @Override
    public void update(Record updatedRecord) {
        DbRequests.update(updatedRecord);
    }

    @Override
    public boolean checkResource(String resource) {
        return getBySource(resource) != null;
    }
}
