package Part2;
import java.util.List;

public class NearestNeighborValidator {
    public static double evaluateAccuracy(List<Instance> dataset, List<Integer> featureSubset) {
        int correctCount = 0;
        int totalCount = dataset.size();

        NearestNeighborClassifier classifier = new NearestNeighborClassifier();
        classifier.train(dataset);

        for (Instance instance : dataset) {
            List<Double> selectedFeatures = instance.getFeatureSubset(featureSubset);
            String predictedLabel = classifier.test(new Instance(selectedFeatures, null));
            String actualLabel = instance.getClassLabel();

            if (predictedLabel.equals(actualLabel)) {
                correctCount++;
            }
        }

        return (double) correctCount / totalCount;
    }
}


class Main {
    public static void main(String[] args) {
        // Read the dataset
        List<Instance> dataset = DatasetReader.readDataset("path/to/dataset.csv");

        // Create and train the classifier
        NearestNeighborClassifier classifier = new NearestNeighborClassifier();
        classifier.train(dataset);

        // Define the feature subset to evaluate
        List<Integer> featureSubset = List.of(0, 2); // Example: evaluating features 0 and 2

        // Evaluate the accuracy
        double accuracy = NearestNeighborValidator.evaluateAccuracy(dataset, featureSubset);
        System.out.println("Accuracy: " + accuracy);
    }
}