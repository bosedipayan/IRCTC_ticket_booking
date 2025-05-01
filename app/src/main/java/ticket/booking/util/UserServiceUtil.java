package ticket.booking.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String password) {
        // Implement your hashing logic here
        return BCrypt.hashpw(password, BCrypt.gensalt()); // Placeholder, replace with actual hashing
    }

    public static Boolean checkPassword(String password, String hashedPassword) {
        // Implement your password checking logic here
        return  BCrypt.checkpw(password, hashedPassword); // Placeholder, replace with actual checking
    }
}
