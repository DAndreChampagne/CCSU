package cs501.xnotbeingused;

import java.util.Arrays;
import java.util.Objects;

public class Course {
	public String Name = null;
	public String Subject = null;
	public int CourseNumber = 0;
	public boolean Required = false;
	public boolean RequiredInstructorPermission = false;
	public Course[] Prerequisites = null;
	
	public boolean InProgress = false;
	public boolean Completed = false;
	
	public Course(String subject,  int courseNumber) {
		Subject = subject;
		CourseNumber = courseNumber;
	}
	
	public Course(String name, String subject, int courseNumber, boolean required, boolean requiresInstructorPermission, Course ... prereqs) {
		Name = name;
		Subject = subject;
		CourseNumber = courseNumber;
		Required = required;
		RequiredInstructorPermission = requiresInstructorPermission;
		Prerequisites = prereqs;
	}
	
	public Course(String name, String subject, int courseNumber, boolean required, Course ... prereqs) {
		Name = name;
		Subject = subject;
		CourseNumber = courseNumber;
		Required = required;
		Prerequisites = prereqs;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(Subject, CourseNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course))
			return false;
		
		Course x = (Course)obj;
		
		return
			x.Subject.equals(x.Subject)
			&& x.CourseNumber == x.CourseNumber;
	}
	
	
	
	@Override
	public String toString() {
//		return Subject + " " + CourseNumber;
		return Subject + " " + CourseNumber + " (" + Name + ")";
//		return "Course [Name=" + Name + ", Subject=" + Subject + ", CourseNumber=" + CourseNumber + ", Required="
//				+ Required + ", RequiredInstructorPermission=" + RequiredInstructorPermission + ", Prerequisites="
//				+ Arrays.toString(Prerequisites) + "]";
	}

	public static boolean PrerequisitesSatisfied(Course c, Course[] completedCourses) throws Exception {
		throw new Exception("Not implemented");
	}
	
}
