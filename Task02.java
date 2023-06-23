import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Даны два файла, в каждом из которых находится запись многочлена.
 * Сформировать файл, содержащий сумму многочленов
 */


public class Task02 {
    public static void main(String[] args) {
        // Считываем имена исходных файлов
        String filename = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Файл с первым многочленом -> ");
        if (sc.hasNextLine()) {
            filename = sc.next();
        }
        String poli1 = Mylib.GetPolinominalFromFile(filename);
        System.out.println(poli1);
        System.out.println("Файл со вторым многочленом -> ");
        if (sc.hasNextLine()) {
            filename = sc.next();
        }
        String poli2 = Mylib.GetPolinominalFromFile(filename);
        System.out.println(poli2);
        sc.close();
        // Получаем коэффициенты каждого из многочленов
        int[] coeffs1 = Mylib.GetCoefficients(poli1);
        int[] coeffs2 = Mylib.GetCoefficients(poli2);
        // Суммируем их
        int maxSize = coeffs1.length > coeffs2.length ? coeffs1.length : coeffs2.length;
        int[] coeffs = new int[maxSize];
        for (int i = 0; i < coeffs.length; i++) {
            int coeff1 = i < coeffs1.length ? coeffs1[i] : 0;
            int coeff2 = i < coeffs2.length ? coeffs2[i] : 0;
            coeffs[i] = coeff1 + coeff2;
        }
        // Выводим результат
        System.out.println("Результат: ");
        System.out.println(Mylib.GetPolinominalAsString(coeffs));
        // Запись файла
        try (FileWriter fw = new FileWriter("result.txt")) {
            fw.append(Mylib.GetPolinominalAsString(coeffs));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
