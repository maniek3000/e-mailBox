package com.emailBox;

import java.util.Scanner;

public class TestUserInterface {
    private final UserControler userControler;

    public TestUserInterface(UserControler userControler) {
        this.userControler = userControler;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Witam w serwisie poczty elektronicznej. Co chcesz zrobić?");
            System.out.println("1- Zalogować się");
            System.out.println("2- Stworzyć nowego użytkownika");
            System.out.println("0- Wyjdź z serwisu");

            int response = scanner.nextInt();

            switch (response) {
                case 1:

                    break;
                case 2:
                    createUser();
                    break;
                case 0:
                    return;

            }
        }
    }

    private void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj login");
        String username = scanner.nextLine();
        System.out.println("Podaj hasło");
        String password = scanner.nextLine();
        String httpResponseBody = userControler.createUser(username, password);
        System.out.println("Odpowiedź z servera: " + httpResponseBody);
    }
}
