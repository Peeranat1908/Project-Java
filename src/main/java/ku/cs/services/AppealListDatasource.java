package ku.cs.services;

import ku.cs.models.Appeal;
import ku.cs.models.AppealList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AppealListDatasource implements Datasource<AppealList>{
    private String filePath;

    public AppealListDatasource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public AppealList readData() {
        AppealList appealList = new AppealList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();


            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String type = parts[0];
                    String subject = parts[1];
                    String request = parts[2];
                    LocalDate date = LocalDate.parse(parts[3]);
                    String signature = parts[4];
                    long timestamp = Long.parseLong(parts[5]);
                    String status = parts[6];
                    LocalTime time = LocalTime.parse(parts[7]);

                    Appeal appeal = new Appeal(type, subject, request, date, signature, timestamp,status,time);
                    appealList.addAppeal(appeal);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the appeals from the CSV file.");
            e.printStackTrace();
        }
        return appealList;
    }


    @Override
    public void writeData(AppealList data) {
        List<Appeal> appeals = data.getsAppeals();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Type,Subject,Request,Date,Signature,Timestamp");
            writer.newLine();

            for (Appeal appeal : appeals) {
                writer.write(appeal.getType() + ","
                        + appeal.getSubject() + ","
                        + appeal.getRequest() + ","
                        + appeal.getDate() + ","
                        + appeal.getStudentSignature() + ","
                        + appeal.getSecond() + ","
                        + appeal.getStatus() + ","
                        + appeal.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the appeals to the CSV file.");
            e.printStackTrace();
        }
    }
}
