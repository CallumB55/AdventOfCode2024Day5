/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.callumhobby.adventofcode2024day5;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author CallumBinns
 */
public class PrintQueue {
    private List<Integer[]> rules;
    private List<List<Integer>> fullQueues;
    private List<List<Integer>> validQueues;
    
    /**
     * 
     * @param stringRules requires a string list of pipe delimited rule pairs
     * @param stringQueues requires a string list of comma delimited pages
     */
    public PrintQueue(List<String> stringRules, List<String> stringQueues ){
        List<Integer[]> rulesList = new ArrayList<>();
        for (String rule : stringRules) {
            Integer[] intRule = {Integer.valueOf(rule.split("\\|")[0]),Integer.valueOf(rule.split("\\|")[1])};
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
        List<List<Integer>> validHolder = new ArrayList<>();
        this.validQueues = validHolder;
        validator();
    }
    
    private void validator(){
        for (List<Integer> queue : fullQueues) {
            boolean validQueue = true;
            for (Integer[] rule : rules) {
                Integer indexOfFirstNum = queue.indexOf(rule[0]);
                Integer indexOfSecondNum = queue.indexOf(rule[1]);
                if (indexOfFirstNum >=0 && indexOfSecondNum >=0) {
                    if (indexOfFirstNum>indexOfSecondNum) {
                        validQueue = false;
                    }
                    
                }
            }
            if (validQueue) {
                validQueues.add(queue);
            }
        }
    }
    
    public Integer middleSum(){
        Integer sumOfMiddles = 0;
        for (List<Integer> queue : validQueues) {
            float middleIndex = queue.size()/2;
            sumOfMiddles += queue.get(Math.round(middleIndex));
        }
                
        return sumOfMiddles;
        
    }
    
}
