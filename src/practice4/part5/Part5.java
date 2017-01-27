package practice4.part5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.Scanner;

/**
 * Задание 5
 * <p>
 * Создать пакеты ресурсов (properties файлы) для двух локализаций: ru и en.
 *
 * @author Nikita Datsenko
 */
public class Part5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input;
        String[] inputData;
        while (!"exit".equals(input = scanner.nextLine())) {
            inputData = input.split(" ");
            localize(inputData[1], inputData[0]);
        }
    }

    public static void localize(String localeName, String key) {
        try (FileInputStream fis = new FileInputStream(
            "E:\\developing\\EPAM_course\\src\\practice4\\resources_" + localeName + ".properties");
             Reader reader = new InputStreamReader(fis, "UTF-8")) {
            Properties properties = new Properties();
            properties.load(reader);
            System.out.println(properties.getProperty(key));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
