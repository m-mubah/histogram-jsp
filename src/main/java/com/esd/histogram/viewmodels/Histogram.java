package com.esd.histogram.viewmodels;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Histogram {
    private int[] data;
    private String[] labels;

    public Histogram(int[] data) {
        this.data = data;

        labels = new String[] {
          "0 - 10", "11 - 20", "21 - 30", "31 - 40", "41 - 50", "51 - 60", "61 - 70", "71 - 80", "81 - 90", "91 - 100"
        };
    }

    public String getData() {
        return Arrays.toString(data);
    }

    public String getLabels() {
        return Arrays.stream(labels).collect(Collectors.joining("\", \"", "\"", "\""));
    }
}
