import java.util.Scanner;

public class CourseDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course course = new Course("CS50x Introduction to Computer Science", 2, 2);

        int choice;
        do {
            System.out.println("What action would you like to take?");
            System.out.println("1. Add a student");
            System.out.println("2. Drop a student");
            System.out.println("3. Print course information");
            System.out.println("4. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent(course, scanner);
                    break;
                case 2:
                    dropStudent(course, scanner);
                    break;
                case 3:
                    System.out.println(course);
                    break;
                case 4:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);
    }

    private static void addStudent(Course course, Scanner scanner) {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's ID: ");
        String id = scanner.nextLine();
        System.out.print("Has the student paid tuition? (Yes/No): ");
        String tuitionPaidInput = scanner.nextLine().toLowerCase();

        boolean tuitionPaid = tuitionPaidInput.equals("yes") || tuitionPaidInput.equals("y");
        
        
        if (course.addStudent(new Student(name, id, tuitionPaid))) {
            System.out.println(name + "(" + id + ")" + " has been added successfully.\n");
        } else {
            System.out.println("Failed to add the student. Roster and waitlist are full.\n");
        }
    }

    private static void dropStudent(Course course, Scanner scanner) {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's ID: ");
        String id = scanner.nextLine();

        if (course.dropStudent(new Student(name, id, true))) {
            System.out.println(name + "(" + id + ")" + " has been dropped successfully.\n");
        } else {
            System.out.println("Failed to drop the student. Student not found.\n");
        }
    }
}
