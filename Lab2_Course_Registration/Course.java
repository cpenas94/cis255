public class Course {
    private String courseName;
    private Student[] roster;
    private Student[] waitlist;
    private int maxRosterSize;
    private int maxWaitlistSize;
    private int numEnrolled;
    private int numWaitlist;

    public Course(String courseName, int maxRosterSize, int maxWaitlistSize) {
        this.courseName = courseName;
        this.maxRosterSize = maxRosterSize;
        this.maxWaitlistSize = maxWaitlistSize;
        this.roster = new Student[maxRosterSize];
        this.waitlist = new Student[maxWaitlistSize];
        this.numEnrolled = 0;
        this.numWaitlist = 0;
    }

    public int getMaxEnrolled() {
        return maxRosterSize;
    }

    public int getMaxWaitlist() {
        return maxWaitlistSize;
    }

    public int getNumEnrolled() {
        return numEnrolled;
    }

    public int getNumWaitlist() {
        return numWaitlist;
    }
    
    @Override
    public String toString() {
        StringBuilder courseInfo = new StringBuilder();
        courseInfo.append("Course: ").append(courseName).append("\n");
        courseInfo.append("Number of students enrolled: ").append(numEnrolled).append("\n");
        courseInfo.append("Number of students on the waitlist: ").append(numWaitlist).append("\n");
        courseInfo.append("Maximum students that can be enrolled: ").append(maxRosterSize).append("\n");
        courseInfo.append("Maximum students that can be on the waitlist: ").append(maxWaitlistSize).append("\n");
        
        courseInfo.append("Roster of enrolled students: " + numEnrolled + "\n");
        for (Student student : roster) {
            if (student != null) {
                courseInfo.append(student).append("\n");
            }
        }
        
        courseInfo.append("Students on the waitlist: " + numWaitlist + "\n");
        for (Student student : waitlist) {
            if (student != null) {
                courseInfo.append(student).append("\n");
            }
        }
        return courseInfo.toString();
    }
    
    
    public boolean addStudent(Student student) {
        if (student.isTuitionPaid()) {
            if (isStudentEnrolled(student) || isStudentOnWaitlist(student)) {
                return false;
            }

            if (numEnrolled < maxRosterSize) {
                roster[numEnrolled] = student;
                numEnrolled++;
                return true;
            } else if (numWaitlist < maxWaitlistSize) {
                waitlist[numWaitlist] = student;
                numWaitlist++;
                return true;
            }
        }
        return false;
    }

    
    public boolean dropStudent(Student student) {
        if (isStudentEnrolled(student)) {
            removeStudentFromRoster(student);
            if (numWaitlist > 0) {
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
        for (int i = 0; i < numEnrolled; i++) {
            if (roster[i] != null && roster[i].equals(student)) {
                return true;
            }
        }
        return false;
    }


    private boolean isStudentOnWaitlist(Student student) {
        for (int i = 0; i < numWaitlist; i++) {
            if (waitlist[i] != null && waitlist[i].equals(student)) {
                return true;
            }
        }
        return false;
    }


    private void removeStudentFromRoster(Student student) {
        for (int i = 0; i < numEnrolled; i++) {
            if (roster[i] != null && roster[i].equals(student)) {
                for (int j = i; j < numEnrolled - 1; j++) {
                    roster[j] = roster[j + 1];
                }
                roster[numEnrolled - 1] = null;
                numEnrolled--;
                break;
            }
        }
    }


    private void removeStudentFromWaitlist(Student student) {
        for (int i = 0; i < numWaitlist; i++) {
            if (waitlist[i] != null && waitlist[i].equals(student)) {
                for (int j = i; j < numWaitlist - 1; j++) {
                    waitlist[j] = waitlist[j + 1];
                }
                waitlist[numWaitlist - 1] = null;
                numWaitlist--;
                break;
            }
        }
    }


    private void enrollFirstWaitlistedStudent() {
        if (numWaitlist > 0) {
            Student student = waitlist[0];
            removeStudentFromWaitlist(student);
            roster[numEnrolled] = student;
            numEnrolled++;
        }
    }

}
