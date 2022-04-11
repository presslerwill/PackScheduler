/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Class for the enrollment of a Course. Contains fields of a LinkedAbstractList
 * to represent all the students enrolled in the course and the enrollment cap
 * for the course. Static final ints represent the minimum and maximum possible
 * enrollment numbers for a course. The constructor creates a new CourseRoll,
 * and implemented methods can set and get the enrollment cap, enroll or drop
 * students from the CourseRoll, get the number of open seats in the Course, and
 * determine if a student can enroll in the Course.
 * 
 * @author Will Pressler
 * @author Jason Wong
 *
 */
public class CourseRoll {

    /** List of the students in a Course */
    private LinkedAbstractList<Student> roll;
    /** The roll's enrollment capacity */
    private int enrollmentCap;
    /** Minimum enrollment for a course */
    static final int MIN_ENROLLMENT = 10;
    /** Maximum enrollment for a course */
    static final int MAX_ENROLLMENT = 250;
    /** Waitlist for this Course */
    private LinkedQueue<Student> waitlist;
    /** Constant for the size of waitlist */
    static final int WAITLIST_SIZE = 10;
    /** Course that this CourseRoll is associated with */
    private Course course;

    /**
     * Creates a roll for a Course
     *
     * @param c             Course that this CourseRoll is associated with
     * @param enrollmentCap the enrollment cap for the Course
     * @throws IllegalArgumentException if the course is null
     */
    public CourseRoll(Course c, int enrollmentCap) {
        if (c == null) {
            throw new IllegalArgumentException();
        }

        roll = new LinkedAbstractList<Student>(MAX_ENROLLMENT);
        setEnrollmentCap(enrollmentCap);
        waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
        course = c;
    }

    /**
     * Gets the current roll's enrollment cap
     * 
     * @return int the enrollment cap
     */
    public int getEnrollmentCap() {
        return this.enrollmentCap;
    }

    /**
     * Sets the enrollment cap for the CourseRoll
     * 
     * @param enrollmentCap the desired enrollment cap
     * @throws IllegalArgumentException if enrollmentCap is less than MIN_ENROLLMENT
     *                                  or greater than MAX_ENROLLMENT or if the
     *                                  roll has more students than the inputed
     *                                  enrollmentCap
     */
    public void setEnrollmentCap(int enrollmentCap) {
        if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
            throw new IllegalArgumentException("Invalid enrollment cap");
        }
        if (enrollmentCap < roll.size()) {
            throw new IllegalArgumentException("Invalid enrollment cap");
        }

        roll.setCapacity(enrollmentCap);
        this.enrollmentCap = enrollmentCap;
    }

    /**
     * Enrolls a Student in the Course, adds them to the roll
     * 
     * @param s the student to be added to the roll
     * @throws IllegalArgumentException if the student is null, if the student is a
     *                                  duplicate, or if the enrollment cap has been
     *                                  reached
     */
    public void enroll(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid student");
        }

        for (int i = 0; i < roll.size(); i++) {
            if (roll.get(i).equals(s)) {
                throw new IllegalArgumentException("Cannot enroll");
            }
        }

        try {
            // checks if the student can enroll in the Course
            if (canEnroll(s)) {
                try {
                    roll.add(s);
                    s.getSchedule().addCourseToSchedule(course);
                    return;
                } catch (IllegalArgumentException e) {
                    waitlist.enqueue(s);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot enroll");
        }
    }

    /**
     * Drops a Student from the Course, removes them from the roll
     * 
     * @param s the student to be removed from the roll
     * @throws IllegalArgumentException if the student cannot be dropped from the
     *                                  roll
     */
    public void drop(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid student");
        }

        // tries to remove the student, catches an exception if it doesn't work
        try {
            // Added one to roll().size so an exception will be thrown if student isn't
            // found in the list
            for (int i = 0; i < roll.size(); i++) {
                if (roll.get(i).equals(s)) {
                    roll.remove(i);
                    if (!waitlist.isEmpty()) {
                        Student nextStudent = waitlist.dequeue();
                        roll.add(nextStudent);
                        nextStudent.getSchedule().addCourseToSchedule(course);
                    }
                    return;
                }
            }

            LinkedQueue<Student> updatedWaitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
            while (!waitlist.isEmpty()) {
                Student studentInWaitlist = waitlist.dequeue();
                if (!studentInWaitlist.equals(s)) {
                    updatedWaitlist.enqueue(studentInWaitlist);
                }
            }
            waitlist = updatedWaitlist;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid student");
        }

    }

    /**
     * Gets the number of open seats in a Course
     * 
     * @return int the number of open seats in a Course
     */
    public int getOpenSeats() {
        return this.enrollmentCap - roll.size();
    }

    /**
     * Determines if a student can enroll in a Course
     * 
     * @param s the student who is trying to enroll in the course
     * @return boolean true if the student can enroll, false if they cannot
     */
    public boolean canEnroll(Student s) {
        // returns false if the roll is at the cap already
        if (getOpenSeats() < 1 && waitlist.size() == WAITLIST_SIZE) {
            return false;
        }
        // returns false if the student to be added is already in the course
        for (int i = 0; i < roll.size(); i++) {
            if (roll.get(i).equals(s)) {
                return false;
            }
        }
        
        LinkedQueue<Student> updatedWaitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
        boolean duplicate = false;
        while (!waitlist.isEmpty()) {
            Student studentInWaitlist = waitlist.dequeue();
            if (studentInWaitlist.equals(s)) {
                duplicate = true;
            }
            updatedWaitlist.enqueue(studentInWaitlist);
        }
        waitlist = updatedWaitlist;
        
        return !duplicate;
    }

    /**
     * Returns the number of students currently on the waitlist for this
     * Course/CourseRoll
     * 
     * @return number of students on waitlist
     */
    public int getNumberOnWaitlist() {
        return waitlist.size();
    }
}
