package com.nt.red_sms_api.Util;

import java.util.Collections;
import java.util.List;

public class Calculate {
    public static double findMedian(List<Double> numbers) {
        // Step 1: Sort the list
        Collections.sort(numbers);

        // Step 2: Find the middle element(s)
        int size = numbers.size();
        if (size % 2 == 1) {
            // If the list size is odd, return the middle element
            return numbers.get(size / 2);
        } else {
            // If the list size is even, return the average of the two middle elements
            Double middle1 = numbers.get((size / 2) - 1);
            Double middle2 = numbers.get(size / 2);
            return (middle1 + middle2) / 2.0;
        }
    }

    public static double findAverage(List<Double> numbers) {
        int sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        return sum / (double) numbers.size();
    }

    public static double findMin(List<Double> numbers) {
        double min = numbers.get(0);
        for (double number : numbers) {
            min = Math.min(min, number);
        }
        return min;
    }


    
    public static double findMax(List<Double> numbers) {
        Double max = Double.MIN_VALUE;
        for (Double number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    
}
