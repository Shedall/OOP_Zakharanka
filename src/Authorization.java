import java.util.*;
import java.io.*;

public class Authorization implements Serializable
{
    List<User> userList = new ArrayList<>();
    boolean haveuser;
    void authorization()
    {
        userList = WritingReadingFiles.readfromfile(userList);
        String userName = UserInputRearer.readuserName();
        haveuser = false;
        for (User user : userList)
        {
            if (userName.equals(user.Get_UserName()))
            {
                if(user instanceof SuperUser) {
                    SuperUser superUser = (SuperUser) user;
                    haveuser = true;
                    do {
                        UserInputRearer.trypassword = false;
                        String password = UserInputRearer.readpassword();
                        if (password.equals(superUser.Get_Password())) {
                            System.out.println("пользователь '" + superUser.Get_UserName() + "' успешно авторизован");
                            user.isautorisation = true;
                            UserInputRearer.trypassword = true;
                            AdminAcount.AdminAcount(superUser);
                        } else {
                            System.out.println("введен неверный пароль,повторите попытку");
                        }
                    } while (!UserInputRearer.trypassword);
                }
                else
                {
                    haveuser = true;
                    do {
                        UserInputRearer.trypassword = false;
                        String password = UserInputRearer.readpassword();
                        if (password.equals(user.Get_Password())) {
                            System.out.println("пользователь '" + user.Get_UserName() + "' успешно авторизован");
                            user.isautorisation = true;
                            UserInputRearer.trypassword = true;
                            PersonalAccount.PersonalAccount(user);
                        } else {
                            System.out.println("введен неверный пароль,повторите попытку");
                        }
                    } while (!UserInputRearer.trypassword);
                }
            }
        }


        if(!haveuser)
        {
            System.out.println("Пользоватиель с данным иминем пользователя не ообнаружен,убедитесь в правильности введеных данных");
            System.out.println("Если вы хотите зарегистрировать нового пользователя введите 'да';для выхода введите любую другую букву:");
            Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine().toLowerCase();
                if(str.equals("да"))
                {
                    registration();
                }
        }

    }


    void authorization(String userName,String password)
    {
        userList = WritingReadingFiles.readfromfile(userList);
        for (User user : userList) {
            if (userName.equals(user.Get_UserName()))
            {

                haveuser = true;
                UserInputRearer.trypassword = false;
                if (password.equals(user.Get_Password()))
                {
                    System.out.println("пользователь '" + user.Get_UserName() + "' успешно авторизован");
                    user.isautorisation = true;
                    UserInputRearer.trypassword = true;
                    PersonalAccount.PersonalAccount(user);
                } else {System.out.println("введен неверный пароль,повторите попытку");}
            }
        }
    }

    void registration() {
        userList = WritingReadingFiles.readfromfile(userList);
        User user = new User();
        boolean unique;
        boolean ex;
        String userName;

        do {
            ex = false;
            userName = UserInputRearer.readuserName();
            unique = true;

            for (User us : userList) {
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
            user.Set_UserName(userName);

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

            userList.add(user);
            WritingReadingFiles.writeToFile(userList);

            authorization(user.Get_UserName(), user.Get_Password());
        }
    }
}

