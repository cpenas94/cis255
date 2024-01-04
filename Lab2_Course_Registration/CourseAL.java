import java.util.ArrayList;

public class CourseAL {
    private String courseName;
    private ArrayList<Student> roster;
    private ArrayList<Student> waitlist;
    private int maxRosterSize;
    private int maxWaitlistSize;

    public CourseAL(String courseName, int maxRosterSize, int maxWaitlistSize) {
        this.courseName = courseName;
        this.maxRosterSize = maxRosterSize;
        this.maxWaitlistSize = maxWaitlistSize;
        this.roster = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    public int getMaxEnrolled() {
        return maxRosterSize;
    }

    public int getMaxWaitlist() {
        return maxWaitlistSize;
    }

    public int getNumEnrolled() {
        return roster.size();
    }

    public int getNumWaitlist() {
        return waitlist.size();
    }

    @Override
    public String toString() {
        StringBuilder courseInfo = new StringBuilder();
        courseInfo.append("Course: ").append(courseName).append("\n");
        courseInfo.append("Number of students enrolled: ").append(getNumEnrolled()).append("\n");
        courseInfo.append("Number of students on the waitlist: ").append(getNumWaitlist()).append("\n");
        courseInfo.append("Maximum students that can be enrolled: ").append(maxRosterSize).append("\n");
        courseInfo.append("Maximum students that can be on the waitlist: ").append(maxWaitlistSize).append("\n");

        courseInfo.append("Roster of enrolled students: ").append(getNumEnrolled()).append("\n");
        roster.forEach(student -> courseInfo.append(student).append("\n"));

        courseInfo.append("Students on the waitlist: ").append(getNumWaitlist()).append("\n");
        waitlist.forEach(student -> courseInfo.append(student).append("\n"));

        return courseInfo.toString();
    }

    public boolean addStudent(Student student) {
        if (student.isTuitionPaid()) {
            if (isStudentEnrolled(student) || isStudentOnWaitlist(student)) {
                return false;
            }

            if (roster.size() < maxRosterSize) {
                roster.add(student);
                return true;
            } else if (waitlist.size() < maxWaitlistSize) {
                waitlist.add(student);
                return true;
            }
        }
        return false;
    }

    public boolean dropStudent(Student student) {
        if (isStudentEnrolled(student)) {
            removeStudentFromRoster(student);
            if (waitlist.size() > 0) {
                enrollFirstWaitlistedStudent();
            }
            return true;
        } else if (isStudentOnWaitlist(student)) {
            removeStudentFromWaitlist(student);
            return true;
        }
        return false;
    }

    private boolean isStudentEnrolled(Student student) {
        return roster.contains(student);
    }

    private boolean isStudentOnWaitlist(Student student) {
        return waitlist.contains(student);
    }

    private void removeStudentFromRoster(Student student) {
        roster.remove(student);
    }

    private void removeStudentFromWaitlist(Student student) {
        waitlist.remove(student);
    }

    private void enrollFirstWaitlistedStudent() {
        if (waitlist.size() > 0) {
            Student student = waitlist.remove(0);
            roster.add(student);
        }
    }
}
