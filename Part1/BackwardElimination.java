package Part1;
import java.util.*;

public class BackwardElimination {
    public static List<Integer> searchBackwardElimination(int featureNum) {
        List<Integer> selectedFeatures = new ArrayList<>();
        for (int i = 1; i <= featureNum; i++) {
            selectedFeatures.add(i);
        }
    
        double bestScore = evaluateFeatureSet(selectedFeatures);
        System.out.println("Using all features " + selectedFeatures + " accuracy is " + String.format("%.1f", (bestScore * 100)) + "%");
        System.out.println("Beginning search.");
    
        while (selectedFeatures.size() > 1) {
            double bestFeatureScore = Double.NEGATIVE_INFINITY;
            int featureToRemove = -1;
    
            for (int feature : selectedFeatures) {
                List<Integer> currentFeatures = new ArrayList<>(selectedFeatures);
                currentFeatures.remove(Integer.valueOf(feature));
                double score = evaluateFeatureSet(currentFeatures);
    
                System.out.println("Using feature(s) " + currentFeatures + " accuracy is " + String.format("%.1f", (score * 100)) + "%");
    
                if (score > bestFeatureScore) {
                    bestFeatureScore = score;
                    featureToRemove = feature;
                }
            }
    
            selectedFeatures.remove(Integer.valueOf(featureToRemove));
            System.out.println("Feature " + featureToRemove + " removed. Best accuracy so far: " + String.format("%.1f", (bestFeatureScore * 100)) + "%");
        }
    
        System.out.println("Finished search!! The best feature subset is " + selectedFeatures + ", which has an accuracy of " + String.format("%.1f", (bestScore * 100)) + "%");
    
        return selectedFeatures;
    }

    public static double evaluateFeatureSet(List<Integer> featureSet) {
        // Stub implementation: Return a random score between 0 and 1
        double score = Math.random();
        return score;
    }
    
}
