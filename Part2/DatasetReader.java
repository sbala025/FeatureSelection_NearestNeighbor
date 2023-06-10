package Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {
    public static List<Instance> readDataset(String filePath) {
        List<Instance> dataset = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+"); // Split values by whitespace
                if (values.length > 1) {
                    String classLabel = values[0];
                    List<Double> featureVector = new ArrayList<>();
                    for (int i = 1; i < values.length; i++) {
                        double feature = Double.parseDouble(values[i]);
                        featureVector.add(feature);
                    }
                    Instance instance = new Instance(featureVector, classLabel);
                    dataset.add(instance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}