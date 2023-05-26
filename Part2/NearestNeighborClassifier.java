package Part2;
import java.util.ArrayList;
import java.util.List;

public class NearestNeighborClassifier {
    private List<Instance> trainingInstances;

    public void train(List<Instance> instances) {
        this.trainingInstances = new ArrayList<>(instances);
    }

    public String test(Instance testInstance) {
        double minDistance = Double.MAX_VALUE;
        String predictedClassLabel = null;

        for (Instance trainInstance : trainingInstances) {
            double distance = calculateEuclideanDistance(testInstance, trainInstance);

            if (distance < minDistance) {
                minDistance = distance;
                predictedClassLabel = trainInstance.getClassLabel();
            }
        }

        return predictedClassLabel;
    }

    private double calculateEuclideanDistance(Instance instance1, Instance instance2) {
        double dist = 0;
        List<Double> featureVector1 = instance1.getFeatureVector();
        List<Double> featureVector2 = instance2.getFeatureVector();
        int dimensions = featureVector1.size();

        for (int i = 0; i < dimensions; i++) {
            double diff = featureVector1.get(i) - featureVector2.get(i);
            dist += Math.pow(diff, 2);
        }

        return Math.sqrt(dist);
    }
    
}

class Instance {
    private List<Double> featureVector;
    private String classLabel;

    // Functions for Instance 
    public Instance(List<Double> featureVector, String classLabel) {
        this.featureVector = featureVector;
        this.classLabel = classLabel;
    }

    public List<Double> getFeatureVector() {
        return featureVector;
    }

    public void setFeatureVector(List<Double> featureVector) {
        this.featureVector = featureVector;
    }

    public String getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(String classLabel) {
        this.classLabel = classLabel;
    }

    public List<Double> getFeatureSubset(List<Integer> featureSubset) {
        List<Double> selectedFeatures = new ArrayList<>();
        for (int index : featureSubset) {
            selectedFeatures.add(featureVector.get(index));
        }
        return selectedFeatures;
    }
}
