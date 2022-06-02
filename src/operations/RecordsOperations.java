package operations;

import model.Record;
import storage.ReceivingData;
import util.ConsoleInteraction;

import java.util.Collection;

public class RecordsOperations {

    private final ReceivingData receivingData;

    public RecordsOperations(ReceivingData receivingData) {
        this.receivingData = receivingData;
    }

    public void add(Record record) {
        receivingData.add(record);
    }

    public void show() {
        Collection<Record> allRecords = receivingData.getRecords();
        ConsoleInteraction.printAllRecords(allRecords);
    }

    public void exit() {
        System.exit(0);
    }

    public Record getRecordByResource(String resource) {
        return receivingData.getBySource(resource);
    }

    public boolean remove(String resource) {
        return receivingData.remove(resource);
    }

    public void update(Record record) {
        receivingData.update(record);
    }

    public boolean isRecordExist(String source) {
        return receivingData.checkResource(source);
    }
}
