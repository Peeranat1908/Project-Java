package ku.cs.services;

import ku.cs.models.MajorEndorser;
import ku.cs.models.MajorEndorserList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MajorEndorserListFileDatasource implements Datasource<MajorEndorserList> {
    private String directoryName;
    private String fileName;

    public MajorEndorserListFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    // ตรวจสอบว่ามีไฟล์ให้อ่านหรือไม่ ถ้าไม่มีให้สร้างไฟล์เปล่า
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public MajorEndorserList readData() {
        MajorEndorserList majorEndorserList = new MajorEndorserList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                String nameId = data[0].trim();
                String positionId = data[1].trim();
                String facultyId = data[2].trim();
                String majorId = data[3].trim();

                majorEndorserList.addNewMajorEndorser(nameId, positionId, facultyId, majorId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return majorEndorserList;
    }

    public void writeData(MajorEndorserList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (MajorEndorser majorEndorser : data.getMajorEndorsers()) {
                String line = majorEndorser.getName() + "," +
                        majorEndorser.getPosition() + "," +
                        majorEndorser.getFaculty() + "," +
                        majorEndorser.getMajor();
                buffer.write(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
