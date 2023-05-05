import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuperUser extends User
{
    List<User> users = new ArrayList<>();
    Authorization authorization = new Authorization();
    //boolean admin = super.admin = true;
    SuperUser()
    {
        super();
        users = WritingReadingFiles.readfromfile(users);
    }

    SuperUser(String username,String password,String name,String lastname,String email,String mobilephone){super(username,password,name,lastname,email,mobilephone);}
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

    void addSuperUser()
    {
        System.out.println("Зарегестрируйте нового администратора:");
        users = WritingReadingFiles.readfromfile(users);
        // User user = new User();
        boolean unique;
        boolean ex;
        String userName;

        do {
            ex = false;
            userName = UserInputRearer.readuserName();
            unique = true;

            for (User us : users) {
                if (userName.equals(us.Get_UserName())) {
                    unique = false;
                    System.out.println("Пользователь с таким Иминем пользователя уже зарегистрирован,придумайте что-нибудь другое");
                    ex = true;
                    break;
                }
            }
            if(ex)
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Для продолжения введите 1");
                System.out.println("Для отмены регистрации введите 2");
                int num = scanner.nextInt();
                if(num ==1) {} else if (num ==2) {break;}
            }
        } while (!unique);
        if(!ex) {
            String password = UserInputRearer.readpassword();

            String name = UserInputRearer.readName();

            String lastname = UserInputRearer.readlastName();

            String email = UserInputRearer.readEmail();

            String numb = UserInputRearer.readmobilenamber();
            SuperUser user = new SuperUser(userName, password, name, lastname, email, numb);
            users.add(user);
            WritingReadingFiles.writeToFile(users);
        }
    }
    void usermanagement()
    {
        System.out.println("Выберите пользователя которым хотите управлять:");
        String username = UserInputRearer.readuserName();

        boolean haveuser = false;
        for(User us :users)
        {
            if(username.equals(us.Get_UserName()))
            {
                authorization.authorization(us.Get_UserName(),us.Get_Password());
                haveuser = true;
            }
        }

        if(!haveuser)
            System.out.println("пользователь с таким иминем пользователя не найден");
    }
}
