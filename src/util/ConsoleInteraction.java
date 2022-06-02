package util;

import model.Record;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleInteraction {

    public static String getStringFromConsole(String messageInConsole) {
        System.out.println(messageInConsole);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void printAllRecords(Collection<Record> records) {
        System.out.println("Resource\tLogin\tPassword");
        for (Record record : records) {
            System.out.println(record);
        }
    }
}
