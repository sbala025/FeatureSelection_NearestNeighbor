package Part2;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighborClassifier {
    private List<Instance> trainingInstances;
    private List<Double> maxFeatureValues;

    public void train(List<Instance> instances) {
        this.trainingInstances = new ArrayList<>(instances);
    
        // Calculate the maximum feature values
        maxFeatureValues = calculateMaxFeatureValues(trainingInstances);
    
        // Normalize the feature values
        normalizeInstances(trainingInstances);
    }
    

    private void normalizeInstances(List<Instance> instances) {
        for (Instance instance : instances) {
            List<Double> normalizedFeatureVector = new ArrayList<>();
            List<Double> featureVector = instance.getFeatureVector();
    
            for (int i = 0; i < featureVector.size(); i++) {
                double featureValue = featureVector.get(i);
                double maxFeatureValue = maxFeatureValues.get(i);
    
                // Perform normalization
                if (maxFeatureValue == 0.0) {
                    normalizedFeatureVector.add(0.0);
                } else {
                    double normalizedValue = featureValue / maxFeatureValue;
                    normalizedFeatureVector.add(normalizedValue);
                }
            }
    
            // Update the instance's normalized feature vector
            instance.setFeatureVector(normalizedFeatureVector);
        }
    }

    public String test(Instance testInstance) {
        double minDistance = Double.MAX_VALUE;
        String predictedClassLabel = "";
    
        for (Instance trainInstance : trainingInstances) {
            double distance = calculateEuclideanDistance(testInstance, trainInstance);
    
            if (distance < minDistance) {
                minDistance = distance;
                predictedClassLabel = trainInstance.getClassLabel();
            }
        }
    
        return predictedClassLabel;
    }

    public double test(List<Instance> testInstances) {
        List<Instance> normalizedTestInstances = new ArrayList<>(testInstances);
        normalizeInstances(normalizedTestInstances);
    
        int correctPredictions = 0;
        int totalInstances = testInstances.size();
    
        for (int i = 0; i < testInstances.size(); i++) {
            String predictedClassLabel = test(normalizedTestInstances.get(i)); // Get the predicted class label
            String trueClassLabel = testInstances.get(i).getClassLabel(); // Get the true class label
    
            if (predictedClassLabel.equals(trueClassLabel)) {
                correctPredictions++;
            }
        }
    
        return (double) correctPredictions / totalInstances;
    }

    private double calculateEuclideanDistance(Instance instance1, Instance instance2) {
        List<Double> featureVector1 = instance1.getFeatureVector();
        List<Double> featureVector2 = instance2.getFeatureVector();
        int dimensions = featureVector1.size();
        double dist = 0;

        for (int i = 0; i < dimensions; i++) {
            double diff = featureVector1.get(i) - featureVector2.get(i);
            dist += Math.pow(diff, 2);
        }

        return Math.sqrt(dist);
    }

    private List<Double> calculateMaxFeatureValues(List<Instance> instances) {
        List<Double> maxValues = new ArrayList<>();
    
        if (instances.isEmpty()) {
            return maxValues; // Return an empty list if there are no instances
        }
    
        int dimensions = instances.get(0).getFeatureVector().size(); // Exclude the class label
    
        for (int i = 0; i < dimensions; i++) {
            double max = Double.MIN_VALUE;
    
            for (Instance instance : instances) {
                double value = instance.getFeatureVector().get(i); // Corrected indexing
                if (value > max) {
                    max = value;
                }
            }
    
            maxValues.add(max);
        }
    
        return maxValues;
    }
}