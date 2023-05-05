import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputRearer
{
    static boolean trypassword = false;
    public static String readuserName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введети имя пользователя:");
        String username = scanner.nextLine();
        return username;
    }

    public static String readpassword()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введети Пароль(В обязательном порядке содержащий буквы и цифры): ");
            String password = scanner.nextLine();
            boolean st = containsLettersAndDigits(password);
            if(st)
            {
                return password;
            }
            else
            {
                System.out.println("Пароль не соответствует обязательным требованиям,пожалуйста,повторите попытку");
                continue;
            }
        }while(true);

    }

    public static String readName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введети имя:");
        String username = scanner.nextLine();
        return username;
    }

    public static String readlastName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введети фамилию:");
        String username = scanner.nextLine();
        return username;
    }

    public static String readEmail()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Введети адрес электронной почты:");
            String email = scanner.nextLine();
            boolean isValid = isValidEmail(email);
            if (isValid)
            {
               return email;
            } else
            {
                System.out.println("Введен некорректный адрес электронной почты,пожалуйста, повторите попытку");
                continue;
            }
        }while(true);
    }

    public static String readmobilenamber()
    {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введети номер телефона,в формате +375 xx xxxxxxx(с учетом пробелов) :");
            String numb = scanner.nextLine();
            boolean isValid = isValidBelarusPhoneNumber(numb);
            if (isValid)
            {
                return numb;
            } else
            {
                System.out.println("неверный формат ввода,повторите попытку");
                continue;
            }
        }while(true);
    }

    public static boolean containsLettersAndDigits(String input) {
        // Паттерн для поиска букв и цифр
        String pattern = "^(?=.*[a-zA-Z])(?=.*\\d).+$";
        return input.matches(pattern);
    }

    public static boolean isValidEmail(String email)
    {
        // Регулярное выражение для проверки адреса электронной почты
        String emailRegex = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+" +
                "[A-Za-z]{2,6}$";

        // Создаем Pattern объект с регулярным выражением
        Pattern pattern = Pattern.compile(emailRegex);

        // Создаем Matcher объект с введенной строкой
        Matcher matcher = pattern.matcher(email);

        // Выполняем проверку и возвращаем результат
        return matcher.matches();
    }

    public static boolean isValidBelarusPhoneNumber(String phoneNumber)
    {
        // Регулярное выражение для проверки номера мобильного телефона Беларуси
        String phoneRegex = "^\\+375\\s?(17|25|29|33|44)\\s?\\d{7}$";

        // Создаем Pattern объект с регулярным выражением
        Pattern pattern = Pattern.compile(phoneRegex);

        // Создаем Matcher объект с введенной строкой
        Matcher matcher = pattern.matcher(phoneNumber);

        // Выполняем проверку и возвращаем результат
        return matcher.matches();
    }
}
