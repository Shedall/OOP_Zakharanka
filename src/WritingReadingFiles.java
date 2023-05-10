import java.io.*;
import java.util.List;

public class WritingReadingFiles
{
    private static List<User> list = null;
    public static List<User> readfromfile(List<User> list)
    {
        try {
            FileInputStream fileIn = new FileInputStream("data.ser"); // Открытие файла для чтения
            ObjectInputStream in = new ObjectInputStream(fileIn); // Создание объектного потока ввода
            while (fileIn.available() > 0) { // Проверка на наличие данных в файле
                list = (List<User>) in.readObject(); // Чтение объекта из потока
            }
            in.close(); // Закрытие объектного потока ввода
            fileIn.close(); // Закрытие файла
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<User> readfromfile() {
        try {
            FileInputStream fileIn = new FileInputStream("data.ser"); // Открытие файла для чтения
            ObjectInputStream in = new ObjectInputStream(fileIn); // Создание объектного потока ввода
            while (fileIn.available() > 0) { // Проверка на наличие данных в файле
                list = (List<User>) in.readObject(); // Чтение объекта из потока
            }
            in.close(); // Закрытие объектного потока ввода
            fileIn.close(); // Закрытие файла
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeToFile(List<User> list) {
        try {
            // Создаем объект FileOutputStream для записи в файл
            FileOutputStream fileOut = new FileOutputStream("data.ser");
            // Создаем объект ObjectOutputStream для записи объектов в поток
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            // Записываем список объектов users в поток
            objectOut.writeObject(list);
            // Закрываем потоки
            objectOut.close();
            fileOut.close();
            System.out.println("Объекты успешно записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
