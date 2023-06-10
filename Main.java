import java.util.*;

import Part1.BackwardElimination;
import Part1.ForwardSelection;
import Part2.DatasetReader;
import Part2.Instance;
import Part2.NearestNeighborClassifier;
import Part2.Validator;

public class Main {
    static int FEATURE_NUM;
    static int ALGORITHM;

    private static void intro() {
        System.out.println("Welcome to Shreya Balaji's Feature Selection Algorithm");

        // Open Scanner
        Scanner sc = new Scanner(System.in);

        // Get feature amount
        FEATURE_NUM = 0;
        while (FEATURE_NUM == 0) {
            System.out.println("Please enter the number of features: ");
            FEATURE_NUM = sc.nextInt();
            if (FEATURE_NUM == 0) {
                System.out.println("Number of features must be more than 0;");
            }
        }

        // Decide which algorithm to run
        ALGORITHM = 0;
        while (ALGORITHM != 1 && ALGORITHM != 2 && ALGORITHM != 3) {
            System.out.println("Type the number of the algorithm you want to run:");
            System.out.println("1 - Forward Selection");
            System.out.println("2 - Backward Elimination");
            System.out.println("3 - Bertie's Special Algorithm");
            ALGORITHM = sc.nextInt();
            if (ALGORITHM != 1 && ALGORITHM != 2 && ALGORITHM != 3) {
                System.out.println("Please choose either 1,2 or 3 for the algorithm.");
            }
        }

        System.out.println("Number of Features: " + Main.FEATURE_NUM);
        System.out.println("Algo Chosen: " + Main.ALGORITHM);

        // Close Scanner
        sc.close();
    }

    private static List<Integer> promptFeatureSubset() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the feature subset (comma-separated feature indices): ");
        String input = scanner.nextLine();

        List<Integer> featureSubset = new ArrayList<>();

        String[] indices = input.split(",");
        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr.trim());
                featureSubset.add(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid index: " + indexStr);
            }
        }
        scanner.close();
        return featureSubset;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String datasetFilePath = "/Users/shreyabalaji/Documents/CS170/FeatureSelection_NearestNeighbor/Dataset/small.txt";
        // Select Part 1 or Part 2
        int partChoice = 0;
        System.out.println("Choose which Part needs to be tested: ");
        partChoice = sc.nextInt();
        switch (partChoice) {
            case 1:
                intro();

                // PART 1 IMPLEMENTATION
                if (Main.ALGORITHM == 1) {
                    ForwardSelection.searchForwardSelection(Main.FEATURE_NUM);
                } else if (Main.ALGORITHM == 2) {
                    BackwardElimination.searchBackwardElimination(Main.FEATURE_NUM);
                }
                break;
            case 2:
                // Read the dataset from file or create it
                List<Instance> dataset = DatasetReader.readDataset(datasetFilePath);

                // Prompt the user for specific features
                List<Integer> featureSubset = promptFeatureSubset();

                // Create a classifier
                NearestNeighborClassifier classifier = new NearestNeighborClassifier();

                // Create a validator
                Validator validator = new Validator(classifier, dataset);

                // Evaluate the feature subset
                double accuracy = validator.evaluateFeatureSubset(featureSubset);

                // Print the accuracy
                System.out.println("Accuracy: " + accuracy);
                break;
            default:
                System.out.println(
                        "Please choose and actual part. I cannot be bothered to make a catch for this mistake so just rerun the file.");

        }

    }

}