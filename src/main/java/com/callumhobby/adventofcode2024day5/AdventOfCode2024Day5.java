/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.callumhobby.adventofcode2024day5;

/**
 *
 * @author CallumBinns
 */
public class AdventOfCode2024Day5 {

    public static void main(String[] args) {
        InputReader rules = new InputReader("src\\main\\java\\com\\callumhobby\\adventofcode2024day5\\Rules.txt");
        InputReader queues = new InputReader("src\\main\\java\\com\\callumhobby\\adventofcode2024day5\\Input.txt");
        
        PrintQueue queue = new PrintQueue(rules.getLines(),queues.getLines());
        
        System.out.println("Sum of middle page numbers for valid queues: "+String.valueOf(queue.middleSum(queue.validQueues)));
        System.out.println("Sum of middle page numbers for fixed queues: "+String.valueOf(queue.middleSum(queue.fixedQueues)));
    }
}
