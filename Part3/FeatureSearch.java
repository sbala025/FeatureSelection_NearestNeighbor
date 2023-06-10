package Part3;
import Part2.*;
import java.util.*;

public class FeatureSearch {
    private List<Instance> dataset;

    public FeatureSearch(List<Instance> dataset) {
        this.dataset = dataset;
    }

    public List<Integer> forwardSelection() {
        List<Integer> featureSubset = new ArrayList<>();
        double bestAccuracy = 0.0;
        List<Integer> bestFeatureSubset = new ArrayList<>();

        for (int i = 0; i < dataset.get(0).getFeatureVector().size(); i++) {
            featureSubset.add(i);

            double accuracy = evaluateFeatureSubset(featureSubset);

            if (accuracy > bestAccuracy) {
                bestAccuracy = accuracy;
                bestFeatureSubset = new ArrayList<>(featureSubset);
            }

            featureSubset.remove(featureSubset.size() - 1);
        }

        return bestFeatureSubset;
    }

    public List<Integer> backwardElimination() {
        List<Integer> featureSubset = new ArrayList<>();

        for (int i = 0; i < dataset.get(0).getFeatureVector().size(); i++) {
            featureSubset.add(i);
        }

        double bestAccuracy = evaluateFeatureSubset(featureSubset);
        List<Integer> bestFeatureSubset = new ArrayList<>(featureSubset);

        for (int i = 0; i < dataset.get(0).getFeatureVector().size(); i++) {
            featureSubset.remove(i);

            double accuracy = evaluateFeatureSubset(featureSubset);

            if (accuracy > bestAccuracy) {
                bestAccuracy = accuracy;
                bestFeatureSubset = new ArrayList<>(featureSubset);
            }

            featureSubset.add(i, i);
        }

        return bestFeatureSubset;
    }

    public double evaluateFeatureSubset(List<Integer> featureSubset) {
        int correctPredictions = 0;
        int totalInstances = dataset.size();
    
        for (int i = 0; i < totalInstances; i++) {
            Instance testInstance = dataset.get(i);
            List<Double> featureVector = testInstance.getFeatureVector();
            List<Double> subsetFeatureVector = new ArrayList<>();
    
            for (int featureIndex : featureSubset) {
                subsetFeatureVector.add(featureVector.get(featureIndex));
            }
    
            Instance subsetInstance = new Instance(subsetFeatureVector, testInstance.getClassLabel());
            List<Instance> trainingSet = new ArrayList<>(dataset);
            trainingSet.remove(i);
    
            NearestNeighborClassifier classifier = new NearestNeighborClassifier();
            classifier.train(trainingSet);
            String predictedClassLabel = classifier.test(subsetInstance);
    
            if (predictedClassLabel.equals(testInstance.getClassLabel())) {
                correctPredictions++;
            }
        }
    
        return (double) correctPredictions / totalInstances;
    }
    

    public static void main(String[] args) {
        String datasetFilePath = "/Users/shreyabalaji/Documents/CS170/FeatureSelection_NearestNeighbor/Dataset/small.txt";
        List<Instance> dataset = DatasetReader.readDataset(datasetFilePath); // Load your dataset here

        FeatureSearch featureSearch = new FeatureSearch(dataset);

        System.out.println("Forward Selection:");
        List<Integer> forwardSelectedFeatures = featureSearch.forwardSelection();
        System.out.println("Selected Features: " + forwardSelectedFeatures);
        System.out.println("Accuracy: " + featureSearch.evaluateFeatureSubset(forwardSelectedFeatures));

        System.out.println("Backward Elimination:");
        List<Integer> backwardSelectedFeatures = featureSearch.backwardElimination();
        System.out.println("Selected Features: " + backwardSelectedFeatures);
        System.out.println("Accuracy: " + featureSearch.evaluateFeatureSubset(backwardSelectedFeatures));
    }
}
