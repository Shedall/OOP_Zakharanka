public class Userinterface extends User
{
    void Showallinformation()
    {
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
    void showlistofelected() {
        for(int in:listofelected) {
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

