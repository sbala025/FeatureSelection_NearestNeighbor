package Part2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {
    public static List<Instance> readDataset(String filePath) {
        List<Instance> instances = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : csvParser) {
                List<Double> featureVector = new ArrayList<>();
                int numFeatures = record.size() - 1;

                for (int i = 0; i < numFeatures; i++) {
                    double featureValue = Double.parseDouble(record.get(i));
                    featureVector.add(featureValue);
                }

                String classLabel = record.get(numFeatures);
                Instance instance = new Instance(featureVector, classLabel);
                instances.add(instance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instances;
    }
}
