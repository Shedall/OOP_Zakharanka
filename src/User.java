import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    protected String userName;
    protected String password;
    String name;
    String lastName;
    String Email;
    String mobilenamber;
    boolean isautorisation;
    //List<Integer> listofelected = new ArrayList<>();

    List<Attraction> listofelected = new ArrayList<>();
    //List<Integer> listofplacesvisited = new ArrayList<>();

    List<Attraction> listofplacesvisited = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    User() {
    }

    ;

    User(String userName, String password, String name, String lastname, String email, String mobilenamber) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastname;
        this.Email = email;
        this.mobilenamber = mobilenamber;
    }

    ;

    String Get_UserName() {
        return userName;
    }

    String Get_Password() {
        return password;
    }

    void Set_UserName(String userName) {
        this.userName = userName;
    }

    void Set_Password(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(this.userName, other.userName)
                && Objects.equals(this.password, other.password)
                && Objects.equals(this.Email, other.Email);
    }

    void Showallinformation() {
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
        System.out.println("+375 (" + mobilenamber.substring(5, 7) + ") " + mobilenamber.substring(8));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Выполняем сериализацию полей по умолчанию
        out.writeObject(password);
        out.writeObject(userName);// Явно сериализуем поле password
    }

    void showlistofelected() {
        for(Attraction in:listofelected) {
            if (GetIDI() == 1) {
                System.out.println("Достопримечательность");
            }
        }
//  }
//        if (listofelected != null && !listofelected.isEmpty()) {
//            for (Attraction attraction : listofelected) {
//                System.out.println(attraction.name);
//            }
//        } else {
//            System.out.println("Список избранных достопримечательностей пуст.");
//        }
    }
    int GetIDI()
    {
        int idi = 1;
        return idi;
    }

    void showlistofplacesvisited() {
//        if (listofplacesvisited != null && !listofplacesvisited.isEmpty()) {
//            for (Attraction attraction : listofplacesvisited) {
//                System.out.println(attraction.name);
//            }
//        }else {
//            System.out.println("Список посещеных мест пуст.");
//        }
//    }
    }
}
