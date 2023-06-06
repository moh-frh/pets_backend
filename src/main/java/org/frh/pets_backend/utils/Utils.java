package org.frh.pets_backend.utils;

import java.util.List;
import java.util.Random;

public class RandomValueFromList {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        Random random = new Random();
        int size = list.size();
        int randomIndex = random.nextInt(size);
        int randomValue = list.get(randomIndex);

        System.out.println("Random value: " + randomValue);
    }
}
