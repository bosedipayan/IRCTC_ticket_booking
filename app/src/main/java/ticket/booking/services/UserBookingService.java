package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDB/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;

        loadUsers();
    }


    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return OBJECT_MAPPER.readValue(users, new TypeReference<List<User>>() {
        });
    }


    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        OBJECT_MAPPER.writeValue(new File(USERS_PATH), userList);
    }

    // json -> object (deserialize)
    // object -> json (serialize)

    public void fetchBooking() {
        // Implement the logic to fetch booking history for the user
        // This could involve reading from a file or a database
        // For now, we'll just print a message

        user.printTickets();
    }

//    cancelbooking will come

    public void cancelBooking() {
        // Implement the logic to cancel a booking
        // This could involve updating a file or a database
        // For now, we'll just print a message

        System.out.println("Booking cancelled successfully.");
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        // Implement the logic to fetch available seats for the train
        // This could involve reading from a file or a database
        // For now, we'll just return an empty list
        return train.getSeats();
    }

    public Boolean bookSeat(Train train, int row, int seat) {
        // Implement the logic to book a seat on the train
        // This could involve updating a file or a database
        // For now, we'll just print a message
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
//                    trainService.updateTrain(train);
//                    System.out.println("Seat booked successfully.");
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception ex) {
            return Boolean.FALSE;
        }
        return null;
    }
}