import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских) в формате А + В: ");
        String Word = scanner.nextLine(); //Word содержит в себе введенную строку, с возможными пробелами, которые введет пользователь, от них нужно избавиться
        scanner.close();
        System.out.println(calc(Word));
    }
    public static String calc(String Word) throws IOException {
        String resultStr;
        if (!Word.contains(" ")) { //если отсутствуют пробелы в строке
            //System.out.println("Введено недопустимое значение");
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
        char last = Word.charAt(Word.length() - 1);
        if(" ".equals(String.valueOf(last))) {
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
        String Word2 = Word.replace(" ", ""); // Word2 содержит введеную строку без пробелов
        String oper;
        String[] operands = Word2.split("[+\\-*/]"); //Разбиение строки на операнды по математическому знаку
        oper = detectOperation(Word2);
        if (operands.length != 2)
            throw new IOException("Должно быть два операнда");//1Условие проверки отсеивает введение более 2 операндов
        //проверки ввода и возарвт ошибок
        String o1 = operands[0];//o1 содержит в себе стринговое значение первого операнда
        String o2 = operands[1];//o2 содержит в себе стринговое значение второго операнда
        if (o1.isEmpty() || o2.isEmpty()) {
            throw new IOException("Должно быть два операнда");
        }//2Условие проверки отсеивает введение менее 2 операндов
        if (Word.indexOf("  ") > 0) {
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }//3Условие проверки отсеивает введение лишних пробелов
        int oo1 = 0;
        int oo2 = 0;
        int rim1 = 0;
        int rim2 = 0;
        String[] Rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};
        //С помощью контрукции делаем попытку преобразования стринговой переменной в интовую и в случае удачи делаем вывод, что
        // первый операнд введен в формате арабских цифр, переменная oo1 является интовым значением первого операнда
        try {
            oo1 = Integer.parseInt(operands[0]);

        }
        //Если преобразование не удалось, делается вывод о том, что 1 операнд или введен в формате римских цифр или первый операнд
        //введен случаным набором символов, в любом случае необходимо делать сопоставление значениям римских цифр
        catch (NumberFormatException e) {
            for (int i = 0; i < Rome.length; i++) {
                if (Rome[i].equals(operands[0])) {
                    oo1 = i + 1;
                    rim1 = 1;
                }
                //В случае если соответствие операнда и набора римских цифр найдено то переменная oo1 является интовым значением первого операнда
                //после чего делается проверка превышает ли значение первого операнда допустимое по заданию значение
                if (oo1 > 10) {
                    throw new IOException("Значения не удовлетворяют заданию - вводимые значения не должны превышать 10 или X");
                }
                //Если соответствия на римскую цифру не найдено делается вывод о том, что первый операнд введен случаным набором символов
            }
            if (rim1 != 1) {
                throw new IOException("Введенное значение не является римскими или арабскими цифрами");
            }
        }

        try {
            oo2 = Integer.parseInt(operands[1]);
        }
        //Если преобразование не удалось, делается вывод о том, что 1 операнд или введен в формате римских цифр или первый операнд
        //введен случаным набором символов, в любом случае необходимо делать сопоставление значениям римских цифр
        catch (NumberFormatException e) {
            for (int i = 0; i < Rome.length; i++) {
                if (Rome[i].equals(operands[1])) {
                    oo2 = i + 1;
                    rim2 = 1;
                }
                //В случае если соответствие операнда и набора римских цифр найдено то переменная oo1 является интовым значением первого операнда
                //после чего делается проверка превышает ли значение первого операнда допустимое по заданию значение
                if (oo2 > 10) {
                    throw new IOException("Значения не удовлетворяют заданию - вводимые значения не должны превышать 10 или X");
                }
                //Если соответствия на римскую цифру не найдено делается вывод о том, что первый операнд введен случаным набором символов
            }
            if (rim2 != 1) {
                throw new IOException("Введенное значение не является римскими или арабскими цифрами");
            }

        }
        if (rim1 != rim2) {
            throw new IOException("используются одновременно разные системы счисления");
        }

        if (oo1>10 || oo2>10){
            throw new IOException ("Значения не удовлетворяют заданию - вводимые значения не должны превышать 10 или X");
        }
        assert oper != null;
        if (oo2==0 & oper.equals("/")) {
            throw new IOException("деление на ноль невозможно");
        }
        int calc = calculate(oo1, oo2, oper);
        if (rim1==0 & rim2==0) {
            resultStr = Integer.toString(calc);
        }
        else if (calc < 1) {
            throw new IOException("в римской системе нет нуля и отрицательных чисел");
        } else {
            resultStr = Rome[calc - 1];
            //System.out.println(calc1);
        }
        return resultStr;
    }

    static String detectOperation (String Word2){
        if (Word2.contains("+")) return "+";
        else if (Word2.contains("-")) return "-";
        else if (Word2.contains("*")) return "*";
        else if (Word2.contains("/")) return "/";
        else return null;
    }
    static int calculate ( int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}



