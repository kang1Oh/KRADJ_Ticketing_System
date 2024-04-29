/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SSTFSimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Janey
 */
public class SSTF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // User input: number of requests and initial head position
        System.out.print("Enter the number of requests: ");
        int numRequests = scanner.nextInt();
        System.out.print("Enter the initial head position: ");
        int initialPosition = scanner.nextInt();
        scanner.nextLine();
        
        // User input: I/O queue requests
        int[] requests = new int[numRequests];
        System.out.println("Enter the I/O queue requests:");
        String input = scanner.nextLine();
        String[] inputStr = input.split("\n");
        for (int i = 0; i < numRequests; i++) {
            requests[i] = Integer.parseInt(inputStr[i]);
        }
        
        // Simulate SSTF disk scheduling algorithm
        int totalSeekTime = 0;
        int currentPosition = initialPosition;
        List<Integer> servedRequests = new ArrayList<>();
        boolean[] visited = new boolean[numRequests];
        Arrays.fill(visited, false);
        
        for (int i = 0; i < numRequests; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = -1;
            
            for (int j = 0; j < numRequests; j++) {
                if (!visited[j]) {
                    int distance = Math.abs(requests[j] - currentPosition);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minIndex = j;
                    }
                }
            }
            
            totalSeekTime += minDistance;
            currentPosition = requests[minIndex];
            visited[minIndex] = true;
            servedRequests.add(requests[minIndex]);
        }
        
        // Output: Order of requests served
        System.out.println("Order of requests served:");
        for (int request : servedRequests) {
            System.out.print(request + " ");
        }
        System.out.println();
        
        // Output: Total seek time
        System.out.println("Total seek time: " + totalSeekTime);
        
        // Output: Average seek time
        double averageSeekTime = (double) totalSeekTime / numRequests;
        System.out.println("Average seek time: " + averageSeekTime);
        
        scanner.close();
    }
}

