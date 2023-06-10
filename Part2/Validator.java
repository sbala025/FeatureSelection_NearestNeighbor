package Part2;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private NearestNeighborClassifier classifier;
    private List<Instance> dataset;

    public Validator(NearestNeighborClassifier classifier, List<Instance> dataset) {
        this.classifier = classifier;
        this.dataset = dataset;
    }

    public double evaluateFeatureSubset(List<Integer> featureSubset) {
        int correctPredictions = 0;

        for (Instance testInstance : dataset) {
            List<Instance> trainingInstances = removeInstanceAtIndex(dataset, dataset.indexOf(testInstance));

            classifier.train(trainingInstances);

            Instance normalizedTestInstance = normalizeInstance(testInstance, featureSubset);

            String predictedClassLabel = classifier.test(normalizedTestInstance);

            if (predictedClassLabel.equals(testInstance.getClassLabel())) {
                correctPredictions++;
            }
        }

        return (double) correctPredictions / dataset.size();
    }

    private List<Instance> removeInstanceAtIndex(List<Instance> instances, int index) {
        List<Instance> modifiedInstances = new ArrayList<>(instances);
        modifiedInstances.remove(index);
        return modifiedInstances;
    }

    private Instance normalizeInstance(Instance instance, List<Integer> featureSubset) {
        List<Double> normalizedFeatureVector = new ArrayList<>();
        List<Double> featureVector = instance.getFeatureVector();

        for (int index : featureSubset) {
            if (index < 0 || index >= featureVector.size()) {
                continue; // Skip invalid indices
            }

            double featureValue = featureVector.get(index);

            normalizedFeatureVector.add(featureValue);
        }

        return new Instance(normalizedFeatureVector, instance.getClassLabel());
    }
}