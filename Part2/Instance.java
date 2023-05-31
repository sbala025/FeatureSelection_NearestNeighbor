package Part2;
import java.util.*;

public class Instance {
    private List<Double> featureVector;
    private String classLabel;

    // Constructor
    public Instance(List<Double> featureVector, String classLabel) {
        this.featureVector = featureVector;
        this.classLabel = classLabel;
    }

    // Getters and setters
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
}