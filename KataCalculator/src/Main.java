import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите пример в формате 'число операция число':");
        String input = scan.nextLine(); // Переменная для введенного примера
        System.out.println("Ответ на пример " + input + " = " + calculator(input)); // Выводим результат.
    }

    // Массив римских цифр от 1 до 100
    public static String[] romanNumerals = {
            "Z", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
            "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"
    };

    public static String calculator(String input) {
        String[] partsOfNumber = input.split(" "); // Разбитие введенного примера
        // Если числа не подходящие, то выходит ошибка
        if (partsOfNumber.length != 3) {
            throw new IllegalArgumentException("Ошибка: Введен пример отличный от варианта ");
        }

        String stringNumber1 = partsOfNumber[0];
        String stringNumber2 = partsOfNumber[2];

        // Проверка на то, что оба числа арабских или оба числа римских
        boolean NumbersRoman = isRomanNumerals(stringNumber1) && isRomanNumerals(stringNumber2);
        boolean NumbersArabic = isArabicNumerals(stringNumber1) && isArabicNumerals(stringNumber2);

        if (!NumbersRoman && !NumbersArabic) {
            throw new IllegalArgumentException("Ошибка: Цифры должны быть арабскими или римскими.");
        }

        int num1, num2, result;
        char operator = partsOfNumber[1].charAt(0);

        if (NumbersRoman) {
            num1 = romanToArabic(stringNumber1);
            num2 = romanToArabic(stringNumber2);
        } else {
            num1 = Integer.parseInt(stringNumber1);
            num2 = Integer.parseInt(stringNumber2);
        }

        // Введенные числа находятся в диапазоне от 1 до 10
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Ошибка: Числа должны быть в диапазоне от 1 до 10.");
        }

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num1 == 0 || num2 == 0) {
                    throw new ArithmeticException("Ошибка: Нельзя делить на ноль.");
                }
                result = num1 / num2;
                break;
            // Если операция отличается от указанных выше, то выпадает ошибка
            default:
                throw new UnsupportedOperationException("Ошибка: Неизвестная операция.");
        }

        // Если введены арабские цифры - ответ выводится арабскими цифрами. Если введены римские цифры - ответ римскими цифрами
        if (NumbersArabic) {
            return String.valueOf(result);
        } else return arabicToRoman(result);
    }

    public static boolean isRomanNumerals(String string) {
        return string.matches("[IVXLC]+");
    }

    public static boolean isArabicNumerals(String string) {
        return string.matches("-?\\d+");
    }

    public static int romanToArabic(String romanNumeral){
        for (int i = 0; i < romanNumerals.length; i++){
            if (romanNumerals[i].equals(romanNumeral)) {
                return i;
            }
        }
        return 0;
    }

    public static String arabicToRoman(int arabicNumeral){
        if (arabicNumeral <= 0){
            throw new IllegalArgumentException("Результат с римскими цифрами не может быть отрицательным");
        }
        return romanNumerals[arabicNumeral];
    }
}
