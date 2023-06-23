import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Задана натуральная степень k. Сформировать случайным образом список коэффициентов 
 * (значения от 0 до 100) многочлена сепени k.
 */

public class Task01 {
    public static void main(String[] args) {
        int k = 0;
        String filename = "";
        // Запрос имени файла
        System.out.print("Имя файла для записи многочлена -> ");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            filename = sc.nextLine();
        }
        // Считывание k
        System.out.print("k -> ");
        if (sc.hasNextInt()) {
            k = sc.nextInt();
        }
        sc.close();
        // Формиование коэффициентов
        int[] coeffs = new int[k];
        Random rnd = new Random();
        for (int i = 0; i < coeffs.length; i++) {
            coeffs[i] = rnd.nextInt(100);
            System.out.println(coeffs[i]);
        }
        // Запись файла
        try (FileWriter fw = new FileWriter(filename)) {
            fw.append(Mylib.GetPolinominalAsString(coeffs));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Mylib.GetPolinominalAsString(coeffs));
    }
}