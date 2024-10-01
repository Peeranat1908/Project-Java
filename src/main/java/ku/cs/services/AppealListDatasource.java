package ku.cs.services;

import ku.cs.models.Appeal;
import ku.cs.models.AppealList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
                if (parts.length == 11) {
                    String studentID = parts[0];
                    String type = parts[1];
                    String subject = parts[2];
                    String request = parts[3];
                    LocalDate date = LocalDate.parse(parts[4]);
                    String signature = parts[5];
                    long timestamp = Long.parseLong(parts[6]);
                    String status = parts[7];
                    LocalTime time = LocalTime.parse(parts[8]);
                    String DeclineReason = parts[9];
                    String majorEndorserSignature = parts[10];


                    Appeal appeal = new Appeal(studentID ,type, subject, request, date, signature, timestamp,status,time ,DeclineReason, majorEndorserSignature);
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
            writer.write("studentID,Type,Subject,Request,Date,Signature,Timestamp,Status,Time,DeclineReason,majorEndorserSignature");
            writer.newLine();
            for (Appeal appeal : appeals) {
                String declineReason = appeal.getDeclineReason();
                String majorEndorserSignature = appeal.getMajorEndorserSignature();
                if (declineReason == null || declineReason.isEmpty())  {
                    declineReason = "\"\"";
                }
                if (majorEndorserSignature == null || majorEndorserSignature.isEmpty()){
                    majorEndorserSignature = "\"\"";
                }
                    writer.write(
                        appeal.getStudentID() + ","
                        + appeal.getType() + ","
                        + appeal.getSubject() + ","
                        + appeal.getRequest() + ","
                        + appeal.getDate() + ","
                        + appeal.getStudentSignature() + ","
                        + appeal.getSecond() + ","
                        + appeal.getStatus() + ","
                        + appeal.getSendtime() + ","
                        + declineReason + ","
                        + majorEndorserSignature);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the appeals to the CSV file.");
            e.printStackTrace();
        }
    }
}
