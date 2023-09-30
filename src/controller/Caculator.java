/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.ScannerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import repository.Algorithm;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class Caculator extends Menu {

    private List<Double> listnumber;
    Algorithm al;
    ScannerFactory sc;
    static String[] mc = {"Calculate Superlative Equation", "Calculate Quadratic Equation", "Exit"};

    public Caculator() {
        super("Equation Program", mc);
        listnumber = new ArrayList<>();
        al = new Algorithm();
        sc = new ScannerFactory();
    }

    public void calculateSuperlative() {
        double a = sc.getNumber("Enter A: ");
        double b = sc.getNumber("Enter B: ");
        double x = al.SuperlativeEquation(a, b);
        System.out.println("Solution X: " + x);
        listnumber.add(a);
        listnumber.add(b);
        listnumber.add(x);
        classifiedNumber(listnumber);
    }

    public void calculateQratic() {
        double a = sc.getNumber("Enter A:");
        double b = sc.getNumber("Enter B:");
        double c = sc.getNumber("Enter C:");
        double delta = al.Delta(a, b, c);
        if (delta < 0) {
            System.out.println("Solution : x = null");
        } else if (delta == 0) {
            double x = -b / 2 * a;
            listnumber.add(x);
            System.out.println("Solution : x1 = x2 = " + x);
        } else {
            double x1 = (-b + Math.sqrt(delta)) / 2 * a;
            double x2 = (-b - Math.sqrt(delta)) / 2 * a;
            listnumber.add(x1);
            listnumber.add(x2);
            System.out.println("Solution : x1 = " + x1 + " and x2 = " + x2);
        }
        listnumber.add(a);
        listnumber.add(b);
        listnumber.add(c);
        classifiedNumber(listnumber);
    }

    public void classifiedNumber(List<Double> numberList) {
//        Odd
        List<Double> oddNumbers = numberList.stream()
                .filter(num -> al.isOdd(num))
                .collect(Collectors.toList());
        System.out.print("Number is Odd: ");
        for (int i = 0; i < oddNumbers.size(); i++) {
            System.out.print(oddNumbers.get(i) + (i < oddNumbers.size() - 1 ? ", " : " "));
        }
        System.out.println(" ");
//        Even
        List<Double> evenNumbers = numberList.stream()
                .filter(num -> !al.isOdd(num))
                .collect(Collectors.toList());
        System.out.print("Number is Even: ");
        for (int i = 0; i < evenNumbers.size(); i++) {
            System.out.print(evenNumbers.get(i) + (i < evenNumbers.size() - 1 ? ", " : " "));
        }
        System.out.println(" ");
//        Perfect
        List<Double> perfectSquare = numberList.stream()
                .filter(num -> al.isPerfect(num))
                .collect(Collectors.toList());
        System.out.print("Number is Perfect Square: ");
        for (int i = 0; i < perfectSquare.size(); i++) {
            System.out.print(perfectSquare.get(i) + (i < perfectSquare.size() - 1 ? ", " : " "));
        }
        System.out.println(" ");
        numberList.clear();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> {
                calculateSuperlative();
            }
            case 2 -> {
                calculateQratic();
            }
            case 3 ->
                System.exit(0);
        }
    }

}
