/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.callumhobby.adventofcode2024day5;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author CallumBinns
 */
public class PrintQueue {

    private List<Integer[]> rules;
    private List<List<Integer>> fullQueues;
    public List<List<Integer>> validQueues;
    private List<List<Integer>> queuesToFix;
    public List<List<Integer>> fixedQueues;

    /**
     *
     * @param stringRules requires a string list of pipe delimited rule pairs
     * @param stringQueues requires a string list of comma delimited pages
     */
    public PrintQueue(List<String> stringRules, List<String> stringQueues) {
        List<Integer[]> rulesList = new ArrayList<>();
        for (String rule : stringRules) {
            Integer[] intRule = {Integer.valueOf(rule.split("\\|")[0]), Integer.valueOf(rule.split("\\|")[1])};
            rulesList.add(intRule);
        }
        this.rules = rulesList;
        List<List<Integer>> initialQueues = new ArrayList<>();
        for (String strQueue : stringQueues) {
            List<Integer> intQueue = new ArrayList<>();
            for (String str : strQueue.split(",")) {
                intQueue.add(Integer.valueOf(str));
            }
            initialQueues.add(intQueue);

        }

        this.fullQueues = initialQueues;
        List<List<Integer>> holder1 = new ArrayList<>();
        this.validQueues = holder1;
        List<List<Integer>> holder2 = new ArrayList<>();
        this.queuesToFix = holder2;
        List<List<Integer>> holder3 = new ArrayList<>();
        this.fixedQueues = holder3;
        validator();
        queueFixer();
    }

    private void validator() {
        for (List<Integer> queue : fullQueues) {
            boolean validQueue = true;
            for (Integer[] rule : rules) {
                Integer indexOfFirstNum = queue.indexOf(rule[0]);
                Integer indexOfSecondNum = queue.indexOf(rule[1]);
                if (indexOfFirstNum >= 0 && indexOfSecondNum >= 0) {
                    if (indexOfFirstNum > indexOfSecondNum) {
                        validQueue = false;
                    }

                }
            }
            if (validQueue) {
                validQueues.add(queue);
            } else {
                queuesToFix.add(queue);
            }
        }
    }

    private void queueFixer() {
        for (List<Integer> queue : queuesToFix) {
            Integer[] q = queue.toArray(new Integer[0]); //learned about fail-fast iterators, avoiding concurrentModificationException
            boolean validQueue = false;
            while (!validQueue) {
                validQueue = true;
                for (Integer[] rule : rules) {
                    Integer indexOfFirstNum = indexOf(q,rule[0]);
                    Integer indexOfSecondNum = indexOf(q,rule[1]);
                    if (indexOfFirstNum >= 0 && indexOfSecondNum >= 0) {
                        if (indexOfFirstNum > indexOfSecondNum) {
                            validQueue = false;
                            q[indexOfFirstNum] = rule[1];
                            q[indexOfSecondNum] = rule[0];//just swap the numbers at those positions
                            
                        }

                    }
                }
               
            }
            if (validQueue) {
                fixedQueues.add(Arrays.asList(q));
            }

        }
    }
    
    private Integer indexOf(Integer[] array, Integer searchNum){//had to swap from lists to arrays but wanted to keep similar functionality for indexOf
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchNum) {
                return i;
            }
        }
        return -1;
    }

    public Integer middleSum(List<List<Integer>> queues) {
        Integer sumOfMiddles = 0;
        for (List<Integer> queue : queues) {
            float middleIndex = queue.size() / 2;
            sumOfMiddles += queue.get(Math.round(middleIndex));
        }

        return sumOfMiddles;

    }

}
