import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите A и B");
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] calcTask = input.split(" ");
        int result = 1;
        int a, b;
        String isNumber1 = IsNumber(calcTask[0]);
        String isNumber2 = IsNumber(calcTask[2]);

        if (isNumber2.equals("Number") && isNumber1.equals("Number")) {
            a = Integer.parseInt(calcTask[0]);
            b = Integer.parseInt(calcTask[2]);
        } else if (isNumber1.equals("Rome Number") && isNumber2.equals("Rome Number")) {
            a = RomeNumbers.valueOf(RomeNumbers.class, calcTask[0]).ordinal() + 1;
            b = RomeNumbers.valueOf(RomeNumbers.class, calcTask[2]).ordinal() + 1;
        } else if (isNumber1.equals("Rome Number") && isNumber2.equals("Number") || isNumber1.equals("Number") && isNumber2.equals("Rome Number")) {
            throw new Exception("Разные системы счисления");
        } else {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (a > 10 || a < 1) throw new Exception("Некорректное число, Введите Число от 1 до 10");
        if (b > 10 || b < 1) throw new Exception("Некорректное число, Введите Число от 1 до 10");

        switch (calcTask[1]) {
            case ("+") -> result = a + b;
            case ("-") -> result = a - b;
            case ("*") -> result = a * b;
            case ("/") -> result = a / b;
            default -> throw new IOException("Неправильный формат операции");
        }

        if (result < 1 && isNumber1.equals("Rome Number")) {
            throw new Exception("В римской системе нет отрицательных чисел");
        }

        if (isNumber1.equals("Number")) return String.valueOf(result);
        return String.valueOf(RomeNumbers.values()[result - 1]);
    }

    static private String IsNumber(String number) {
        try {
            Integer.parseInt(number);
            return "Number";
        } catch (NumberFormatException e) {
            try {
                int number2 = RomeNumbers.valueOf(RomeNumbers.class, number).ordinal() + 1;
                return "Rome Number";
            } catch (Exception ex) {
                return " Not Number";
            }
        }
    }
}