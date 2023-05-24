package Part1;
import java.util.*;

public class ForwardSelection {

    
    public static List<Integer> searchForwardSelection(int featureNum) {
        List<Integer> selectedFeatures = new ArrayList<>();
        Set<Integer> currentFeatures = new HashSet<>();
        double bestScore = Double.NEGATIVE_INFINITY;

        double noFeaturesScore = evaluateFeatureSet(new ArrayList<>());
        System.out.println("Using no features and \"random\" evaluation, I get an accuracy of " + String.format("%.1f", (noFeaturesScore * 100)) + "%");
        System.out.println("Beginning search.");

        for (int i = 1; i <= featureNum; i++) {
            double bestFeatureScore = Double.NEGATIVE_INFINITY;
            int bestFeature = -1;
            List<Integer> currentFeaturesList = new ArrayList<>(currentFeatures);

            for (int feature = 1; feature <= featureNum; feature++) {
                if (!currentFeatures.contains(feature)) {
                    currentFeaturesList.add(feature);
                    double score = evaluateFeatureSet(currentFeaturesList);

                    System.out.println("Using feature(s) " + currentFeaturesList + " accuracy is " + String.format("%.1f", (score * 100)) +"%.");
                    if (score > bestFeatureScore) {
                        bestFeatureScore = score;
                        bestFeature = feature;
                    }
                    currentFeaturesList.remove(currentFeaturesList.size() - 1);
                }
            }

            currentFeatures.add(bestFeature);
            selectedFeatures.add(bestFeature);

            System.out.println("Feature set " + selectedFeatures + " was best, accuracy is " + String.format("%.1f", (bestFeatureScore * 100)) + "%\n");

            if (bestFeatureScore > bestScore) {
                bestScore = bestFeatureScore;
            }
        }

        System.out.println("(Warning, Accuracy has decreased!)");
        System.out.println("Finished search!! The best feature subset is " + selectedFeatures + ", which has an accuracy of " + String.format("%.1f", (bestScore * 100)) + "%");

        return selectedFeatures;
    }

    public static double evaluateFeatureSet(List<Integer> featureSet) {
        // Stub implementation: Return a random score between 0 and 1
        double score = Math.random();
        return score;
    }
    
}
