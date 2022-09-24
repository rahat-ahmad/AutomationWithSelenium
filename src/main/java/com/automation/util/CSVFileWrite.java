package com.automation.util;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CSVFileWrite {
    public void resultInFile(List<String> resultList) throws IOException {
        String fileLocation = "WrongApartmentResult/wrongCostApartmentList.csv";
        File file = new File(fileLocation);
        FileWriter pw = new FileWriter(file);
        CSVWriter writer = new CSVWriter(pw);
        String[] header = {"Apartment Name"};
        writer.writeNext(header);

        for (String apartmentName : resultList) {
            String[] result = {apartmentName};
            writer.writeNext(result);
        }

        writer.close();
    }
}
