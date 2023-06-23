import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Mylib {

    /**
     * Метод для вывода многочлена в виде строки
     */

    public static String GetPolinominalAsString(int[] coeffs) {
        String str = "";
        for (int i = coeffs.length - 1; i >= 0; i--) {
            if (coeffs[i] != 0) {
                if (coeffs[i] != 1 || i == 0) {
                    str = str.concat(String.format("%d", coeffs[i]));
                }
                if (coeffs[i] != 1 && i != 0) {
                    str = str.concat("*");
                }
                if (i > 1) {
                    str = str.concat(String.format("x^%d", i));
                } else if (i == 1) {
                    str = str.concat(String.format("x", i));
                }
                boolean isLast = true; 
                for (int j = i - 1; j >= 0; j--) {
                    if (coeffs[j] > 0) isLast = false;
                }
                if (!isLast) {
                    str = str.concat(" + ");
                }
            }
        }
        return str;
    }

    /**
     * Метод для считывания многочлена из тестового файла
     */

    public static String GetPolinominalFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String str = br.readLine();
            br.close();
            if (str != null) return str;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    /**
     * Метод для определения коэффициентов многочлена
     */

    public static int[] GetCoefficients(String poli) {
        String[] parts = poli.split("\\+"); 
        int maxPow = 0;
        // Определяем максимальную степень в многочлене
        for (String str : parts) {
            if (str.indexOf("^") > 0) {
                int pow = 0;
                String powStr = str.split("\\^")[1]; 
                try {
                    pow = Integer.parseInt(powStr.trim());
                    if (pow > maxPow) {
                        maxPow = pow;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());                    
                }
            }
        }
        // Формирум массив коэффициентов
        int coeffs[] = new int[maxPow + 1];
        for (String str : parts) {
            String powStr = "0";
            String coeffStr = "0";
            if (str.indexOf("x") > 0) {
                coeffStr = str.split("x")[0];
                coeffStr = coeffStr.replace("*","");
                powStr = str.split("x")[1];
                powStr = powStr.replace("^","");
                if (powStr.isBlank()) powStr = "1";
            }    
            else {
                coeffStr = str;
                powStr = "0";
            }    
            try {
                int pow = Integer.parseInt(powStr.trim());
                int coeff = Integer.parseInt(coeffStr.trim());
                coeffs[pow] = coeff;
            } catch (Exception e) {
                System.out.println(e.getMessage());                    
            }                
        }
        return coeffs;
    }

    
}