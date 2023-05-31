package Part2;
import java.util.List;

import java.util.ArrayList;

public class Validator {
    private NearestNeighborClassifier classifier;
    private List<Instance> dataset;

    public Validator(NearestNeighborClassifier classifier, List<Instance> dataset) {
        this.classifier = classifier;
        this.dataset = dataset;
    }

    public double evaluateFeatureSubset(List<Integer> featureSubset) {
        int correctPredictions = 0;

        for (int i = 0; i < dataset.size(); i++) {
            Instance testInstance = dataset.get(i);
            List<Double> testFeatureVector = extractFeatureSubset(testInstance.getFeatureVector(), featureSubset);
            Instance modifiedTestInstance = new Instance(testFeatureVector, testInstance.getClassLabel());

            List<Instance> trainingInstances = removeInstanceAtIndex(dataset, i);
            classifier.train(trainingInstances);

            String predictedClassLabel = classifier.test(modifiedTestInstance);
            String actualClassLabel = testInstance.getClassLabel();

            if (predictedClassLabel.equals(actualClassLabel)) {
                correctPredictions++;
            }
        }

        return (double) correctPredictions / dataset.size();
    }

    private List<Double> extractFeatureSubset(List<Double> featureVector, List<Integer> featureSubset) {
        List<Double> subsetFeatureVector = new ArrayList<>();

        for (int index : featureSubset) {
            subsetFeatureVector.add(featureVector.get(index));
        }

        return subsetFeatureVector;
    }

    private List<Instance> removeInstanceAtIndex(List<Instance> instances, int index) {
        List<Instance> modifiedInstances = new ArrayList<>(instances);
        modifiedInstances.remove(index);
        return modifiedInstances;
    }
}