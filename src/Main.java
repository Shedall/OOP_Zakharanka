import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Authorization authorization = new Authorization();
        Scanner scanner = new Scanner(System.in);

        ///////////////////////////////////////////////////////////////
//        List<User> userList = new ArrayList<>();
//        userList = WritingReadingFiles.readfromfile(userList);
//        SuperUser admin = new SuperUser("Shedall","seven777","Никита","Захаренко","nikokozorro@mail.ru","+375 29 8658999");
//        userList.add(admin);
//        WritingReadingFiles.writeToFile(userList);
//
//        Attraction attraction1 = new Attraction("Национальный музей истории Беларуси", "ул. Карла Маркса, 12", 53.904851, 27.557729);
//        Attraction attraction2 = new Attraction("Парк Челюскинцев", "ул. Челюскинцев, 38", 53.927455, 27.569416);
//        Attraction attraction3 = new Attraction("Брестская крепость", "ул. Циолковского, 1", 52.089229, 23.688131);
//        Attraction attraction4 = new Attraction("Мирский замок", "ул. Замковая, 2", 53.451679, 26.467967);
//        Attraction attraction5 = new Attraction("Беловежская пуща", "Беловежская пуща", 52.658697, 23.854508);
//
//        User user1 = new User("Петя","q12345","Петя","иванов","Pety@bsuir.by","+375 25 5355555");
//        userList.add(user1);
//        user1.listofelected.add(attraction1);
//        user1.listofelected.add(attraction2);
//        user1.listofplacesvisited.add(attraction3);
//
//        User user2 = new User("Коля","q12345","Коля","Сидоров","Coly@bsuir.by","+375 25 3533333");
//        user2.listofplacesvisited.add(attraction4);
//        user2.listofplacesvisited.add(attraction5);
//        userList.add(user2);
//        WritingReadingFiles.writeToFile(userList);
        /////////////////////////////////////////////////////////////

        // authorization.file();
        while(true)
        {
            System.out.println("Для авторизации введите 1");
            System.out.println("Для регистрации введите 2");
            System.out.println("Для вывода информации про Беларусь введите 3");
            System.out.println("Для открытия настроек введите 4");
            System.out.println("Для выхода введите 5");
            int num = scanner.nextInt();
            switch (num)
            {
                case 1:
                    authorization.authorization();
                    break;
                case 2:
                    authorization.registration();
                    break;
                case 3:
                    System.out.println("Беларусь — это страна с древней историей и богатыми традициями; с великолепной природой и удивительной архитектурой, которая сочетает в себе наследие западноевропейских и восточнославянских традиций зодчества.\n" + " Беларусь славится своими лесами и озерами – недаром ее называют «легкие Европы». Беларусь – это широкие светлые проспекты и узкие мощеные улочки, католические костелы и православные соборы, величественные замки и деревянные хаты в деревнях, сытная и вкусная национальная кухня.\n" +
                            "Республика Беларусь расположена в центре Европы, в центральноевропейском временном поясе: GMT + 3 часа.\n" +
                            "География Беларуси : Беларусь расположена в центре Европы, имеет общие границы с пятью государствами: Россия, Украина, Польша, Литва и Латвия.\n" + " Территория страны занимает площадь около 207,6 тысяч квадратных километров: протяженность границ государства с запада на восток составляет 650 км, а с севера на юг - 560 км. Страна не имеет выхода к морю, но благодаря своему географическому положению является важным торговым и транспортным коридором между Европой и странами СНГ.\n" + " Беларусь находится на пересечении трансъевропейских транспортно-коммуникационных коридоров «Запад – Восток» и «Север – Юг».");
                    break;
                case 4:
                    System.out.println("Настройки");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("введено неверное выражение,пожалуйста повторите попытку");

            }

        }
    }
}