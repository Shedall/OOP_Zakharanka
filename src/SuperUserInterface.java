public class SuperUserInterface extends SuperUser
{
    void showallusers()
    {
        users = WritingReadingFiles.readfromfile(users);
        for(User user : users)
        {
            if(user instanceof SuperUser) {
                System.out.print(user.Get_UserName());
                System.out.println("(администратор)");
            }
            else
                System.out.println(user.Get_UserName());
        }
    }
    void Showallinformation()
    {
        System.out.println("ВЫ ЯВЛЯЕТЕСЬ АДМИНИСТРАТОРОМ");
        System.out.print("Имя пользователя: ");
        System.out.println(userName);
        System.out.print("Пароль: ");
        System.out.println(password);
        System.out.print("Имя: ");
        System.out.println(name);
        System.out.print("Фамилия: ");
        System.out.println(lastName);
        System.out.print("Электронная почта: ");
        System.out.println(Email);
        System.out.print("Номер телефона: ");
        System.out.println("+375 (" + mobilenamber.substring(5,7) + ") " + mobilenamber.substring(8 ));
    }
}
