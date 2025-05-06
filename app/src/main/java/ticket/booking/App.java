package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.TrainService;
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

            Train trainSelectedForBooking = new Train();
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
                case 3:
                    System.out.println("Fetching bookings for user: " );
                    userBookingService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Enter source");
                    String source = sc.next();
                    System.out.println("Enter destination");
                    String destination = sc.next();
//                    System.out.println("Searching for trains from " + source + " to " + destination);
                    List<Train> trains = userBookingService.getTrains(source, destination);
                    int index = 1;
                    for (Train train : trains) {
                        System.out.println(index+" Train id : "+train.getTrainId());
                        for (Map.Entry<String, String> entry: train.getStationTimes().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Enter the row number you want to book");
                    int rowNumber = sc.nextInt();
                    System.out.println("Enter the seat number you want to book");
                    int seatNumber = sc.nextInt();

                    break;
            }
        }
    }
}
