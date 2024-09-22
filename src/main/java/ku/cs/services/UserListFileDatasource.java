package ku.cs.services;

import ku.cs.models.UserList;
import ku.cs.models.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UserListFileDatasource implements Datasource<UserList> {

    private String directory;
    private String filename;

    public UserListFileDatasource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = directory + File.separator + filename;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public UserList readData() {
        UserList userList = new UserList();
        String filePath = directory + File.separator + filename;
        File file = new File(filePath);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            while ((line = buffer.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                String username = data[0].trim();
                String password = data[1].trim();
                String name = data[2].trim();
                String surname = data[3].trim();
                LocalDate lastLoginDate = null;
                LocalTime lastLoginTime = null;

                if (data.length > 4 && !data[4].trim().isEmpty()) {
                    lastLoginDate = LocalDate.parse(data[4].trim(), dateFormatter);
                }

                if (data.length > 5 && !data[5].trim().isEmpty()) {
                    lastLoginTime = LocalTime.parse(data[5].trim(), timeFormatter);
                }
                String role = data.length > 6 ? data[6].trim() : null;
                String profilePicturePath = data[7].trim();
                boolean banned = data.length > 8 ? Boolean.parseBoolean(data[8].trim()) : false;
                String faculty = data.length > 9 ? data[9].trim() : null;
                String department = data.length > 10 ? data[10].trim() : null;

                User user = new User(name, surname, username, password, lastLoginDate, lastLoginTime, role, profilePicturePath,banned,faculty,department);
                userList.addUser(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public void writeData(UserList userList) {
        String filePath = directory + File.separator + filename;
        File file = new File(filePath);

        try (BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            for (User user : userList.getUsers()) {
                String profilePicturePath = user.getProfilePicturePath() != null ? user.getProfilePicturePath() : "/images/default-profile-pic.jpg";

                String line = String.join(",",
                        user.getUsername(),
                        user.getPassword(),
                        user.getName(),
                        user.getSurname(),
                        user.getLastLoginDate() != null ? user.getLastLoginDate().format(dateFormatter) : "",
                        user.getLastLoginTime() != null ? user.getLastLoginTime().format(timeFormatter) : "",
                        user.getRole(),
                        profilePicturePath,
                        String.valueOf(user.isBanned())
                        user.getDepartment()
                        user.getFaculty()
                );
                buffer.write(line);
                buffer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
