import java.util.*;

public class Main {
    static int FEATURE_NUM = 0;
    static int ALGORITHM = 0;

    private static void intro() {
        System.out.println("Welcome to Shreya Balaji's Feature Selection Algorithm");
        
        // Open Scanner
        Scanner sc = new Scanner(System.in);

        // Get feature amount
        int FEATURE_NUM = 0;
        while(FEATURE_NUM ==0){
            System.out.println("Please enter the number of features: ");
            FEATURE_NUM = sc.nextInt();
                if(FEATURE_NUM == 0){
                    System.out.println("Number of features must be more than 0;");
                }
        }

        // Decide which algorithm to run
        int ALGORITHM = 0;
        while(ALGORITHM != 1 && ALGORITHM != 2 && ALGORITHM != 3){
            System.out.println("Type the number of the algorithm you want to run:");
            System.out.println("1 - Forward Selection");
            System.out.println("2 - backward Elimination");
            System.out.println("3 - Bertie's Special Algorithm");
            ALGORITHM = sc.nextInt();
            if(ALGORITHM != 1 && ALGORITHM != 2 && ALGORITHM != 3){
                System.out.println("Please choose either 1,2 or 3 for the algorithm.");
            }
        }

        // Close Scanner
        sc.close();
    }
    public static void main(String[] args) {
        intro();

        // PART 1 IMPLEMENTATION
    }

}