import model.Operations;
import model.Record;
import operations.RecordsOperations;
import storage.ReceivingData;
import storage.impl.DatabaseStorage;
import util.ConsoleInteraction;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> possibleOperations = Arrays.stream(Operations.values()).map(Operations::toString).collect(Collectors.toList());
        ReceivingData implOfReceivingData = new DatabaseStorage();
        RecordsOperations operations = new RecordsOperations(implOfReceivingData);
        String operation;
        String resource;
        String login;
        String password;
        Record record;

        while (true) {
            do {
                operation = ConsoleInteraction.getStringFromConsole("Enter desired operation (add/print/exit/update/get/remove)");
            } while (!possibleOperations.contains(operation.toUpperCase()));

            switch (operation) {
                case "add":
                    resource = ConsoleInteraction.getStringFromConsole("Enter resource");
                    login = ConsoleInteraction.getStringFromConsole("Enter login");
                    password = ConsoleInteraction.getStringFromConsole("Enter password");

                    record = new Record(resource, login, password);
                    operations.add(record);
                    break;
                case "print":
                    operations.show();
                    break;
                case "exit":
                    operations.exit();
                case "update":
                    resource = ConsoleInteraction.getStringFromConsole("Enter resource that you would like to update");

                    if(!operations.isRecordExist(resource)) {
                        System.out.println("Sorry, but you resource doesn't exist.");
                    } else {
                        login = ConsoleInteraction.getStringFromConsole("Enter login");
                        password = ConsoleInteraction.getStringFromConsole("Enter password");

                        record = new Record(resource, login, password);
                        operations.update(record);
                    }
                    break;
                case "remove":
                    resource = ConsoleInteraction.getStringFromConsole("Enter resource");
                    System.out.println(operations.remove(resource) ? "removed successfully" : "There is no such resource in storage");
                    break;
                case "get":
                    resource = ConsoleInteraction.getStringFromConsole("Enter resource");
                    Record output = operations.getRecordByResource(resource);
                    System.out.println(output == null ? "There is no such resource in storage" : output.toString());
            }
        }
    }
}
