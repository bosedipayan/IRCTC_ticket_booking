package ticket.booking;

import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.util.*;
import java.util.stream.Collectors;

public class App {
//    public String getGreeting() {
//        return "Hello World!";
//    }

    public static void main(String[] args) {

        System.out.println("Running Train Booking System!!!");

        Scanner sc = new Scanner(System.in);
        int option = 0;

        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        } catch (Exception e) {
            System.out.println("Error initializing UserBookingService: " + e.getMessage());
            return;
        }

        while(option != 7)
        {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter your name");
                    String name = sc.next();
                    System.out.println("Enter your password");
                    String password = sc.next();
                    User userToSignup = new User(name, password, UserServiceUtil.hashPassword(password),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter your name");
                    String loginName = sc.next();
                    System.out.println("Enter your password");
                    String loginPassword = sc.next();
                    User userToLogin = new User(loginName, loginPassword, UserServiceUtil.hashPassword(loginPassword),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }catch (Exception ex){
                        return;
                    }
                    break;
            }
        }
    }
}
