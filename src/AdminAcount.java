import java.util.Scanner;

public class AdminAcount extends PersonalAccount
{
    static void AdminAcount(SuperUser user)
    {
        Scanner scanner = new Scanner(System.in);
        Authorization authorization = new Authorization();
        boolean tr = true;
        while (tr)
        {
            System.out.println("Для отображения информации об пользователи введите 1");
            System.out.println("Для редактирования данных пользователя введите 2");
            System.out.println("Для вывода списка всех пользователей введите 3");
            System.out.println("Для управления пользователем введите 4");
            System.out.println("Для добавления нового пользователя введите 5");
            System.out.println("Для добавления администратора введите 6");
            System.out.println("Для Удаления личного кабинета пользователя введите 7");
            System.out.println("Для выхода из личного кабинета введите 8");
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
                    user.showallusers();
                    break;
                case 4:
                    user.usermanagement();
                    break;
                case 5:
                    authorization.registration();
                    break;
                case 6:
                    user.addSuperUser();
                    break;
                case 7:
                    userdel(user);
                    user.isautorisation = false;
                    tr = false;
                    break;
                case 8:
                    user.isautorisation = false;
                    tr = false;
                    break;
                default:
                    System.out.println("введено неверное выражение,пожалуйста повторите попытку");

            }
        }
    }
}
