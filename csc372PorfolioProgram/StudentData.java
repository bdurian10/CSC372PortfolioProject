package csc372PorfolioProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentData {
	//Main Method
    public static void main(String[] args) {
        LinkedList<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        //Loop asks for user input until 'exit' is entered at the beginning of the loop
        while (true) {
            System.out.println("Enter student data (or type 'exit' to finish):");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            //Break case
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();

            double gpa;
            //Loops GPA entry until a valid GPA is entered
            while (true) {
                System.out.print("GPA (0.00 - 4.00): ");
                //try-catch block validates GPA is between 0.00-4.00 and catches NumberFormatExceptions
                try {
                    gpa = Double.parseDouble(scanner.nextLine());
                    //Break case
                    if (gpa >= 0 && gpa <= 4.00) {
                        break;
                    } else {
                        System.out.println("Invalid GPA. Please enter a value between 0 and 4.00.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numerical value for GPA.");
                }
            }
            //Add complete student entry to LinkedList
            students.add(new Student(name, address, gpa));
        }
        
        //Close scanner
        scanner.close();
        //Use Collections sort method to sort list of students
        //Comparator interface is implemented using lambda expression to simplify code and increase readability
        Collections.sort(students, (student1, student2) -> student1.getName().compareToIgnoreCase(student2.getName()));
        
        //Write student data to text file
        try {
            writeStudentsToFile(students);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    //Custom method for writing LinkedList to a .txt file
    private static void writeStudentsToFile(LinkedList<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));
        
        //Loop through students in LinkedList and write to file
        for (Student student : students) {
            writer.write(student.toString());
            writer.newLine();
        }

        writer.close();
        System.out.println("Student data has been written to students.txt");
    
    }

    //Private static class Student contained within class StudentData to restrict access to Student class
    private static class Student{
        private String name;
        private String address;
        private double gpa;
    	
    	//Parameterized Constructor
    	public Student(String name, String address, double gpa) {
    		this.name = name;
    		this.address = address;
    		this.gpa = gpa;
    	}
    	
    	//Getters and Setters
    	public String getName() {
    		return name;
    	}
        
        //Override toString method to print Student data 
        @Override
        public String toString() {
            return "Name: " + name + ", Address: " + address + ", GPA: " + gpa;
        }

    }
}

