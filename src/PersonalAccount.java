import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalAccount
{
    public static void PersonalAccount(User user)
    {
        Scanner scanner = new Scanner(System.in);
        boolean tr = true;
        while (tr)
        {
            System.out.println("Для отображения информации об пользователи введите 1");
            System.out.println("Для редактирования данных пользователя введите 2");
            System.out.println("Для Удаления личного кабинета пользователя введите 3");
            System.out.println("Для открытия карты нажмите 4");
            System.out.println("Для просмотра списка избраного нажмите 5");
            System.out.println("Для просмотра поездок нажмите 6");
            System.out.println("Для выхода из личного кабинета введите 7");
            int num = scanner.nextInt();
            switch (num)
            {
                case 1:
                    user.Showallinformation();
                    break;
                case 2:
                    useredit(user);
                    break;
                case 3:
                    userdel(user);
                    user.isautorisation = false;
                    tr = false;
                    break;
                case 4:
                    System.out.println("Карта");
                    break;
                case 5:
                    user.showlistofelected();
                    break;
                case 6:
                    user.showlistofplacesvisited();
                    break;
                case 7:
                    user.isautorisation = false;
                    tr = false;
                    break;
                default:
                    System.out.println("введено неверное выражение,пожалуйста повторите попытку");

            }
        }
    }



    static void useredit(User user)
    {
        List<User> userList = WritingReadingFiles.readfromfile();
        userList.remove(user);

        user.Set_UserName(user.Get_UserName());

        String password = UserInputRearer.readpassword();
        user.Set_Password(password);

        String name = UserInputRearer.readName();
        user.name = name;

        String lastname = UserInputRearer.readlastName();
        user.lastName = lastname;

        String email = UserInputRearer.readEmail();
        user.Email = email;

        String numb = UserInputRearer.readmobilenamber();
        user.mobilenamber = numb;

        //userList.remove(user);
        userList.add(user);
        WritingReadingFiles.writeToFile(userList);
    }

    static void userdel(User user)
    {
        List<User> userList = WritingReadingFiles.readfromfile();
        userList.remove(user);
        WritingReadingFiles.writeToFile(userList);
    }
}
