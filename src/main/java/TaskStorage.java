import java.io.*;
import java.util.ArrayList;

public class TaskStorage {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tasks);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // File not found, return empty list
        }

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            tasks = (ArrayList<Task>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting a new task list.");
        } catch (InvalidClassException | ClassNotFoundException e) {
            System.out.println("Task data is corrupted or in an incompatible format.");
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }

        return tasks;
    }
}