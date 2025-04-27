package ticket.booking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

//        System.out.println(new org.example.App().getGreeting());

        List<Integer> arr = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> l = arr.stream().filter(e -> e%2 == 0).collect(Collectors.toList());
    }
}
