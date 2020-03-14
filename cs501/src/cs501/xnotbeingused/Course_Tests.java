package cs501.xnotbeingused;

import static org.junit.jupiter.api.Assertions.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Course_Tests {

	Course[] _mastersClasses;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// classes viewed 3 March 2020
		_mastersClasses = new Course[] {
				
			new Course("Foundations in Computer Science", "CS", 501, true),
			
			new Course("Design Patters", "CS", 505, true, 
					new Course("CS", 501)),
			
			new Course("Software Testing and Quality Assurance", "CS", 506, true, 
					new Course("CS", 501)),
			
			new Course("Distributed Computing", "CS", 508, true, 
					new Course("CS", 501)),
			
			new Course("Fundamentals of Software Engineering", "CS", 510, true, 
					new Course("CS", 501)),
			
			new Course("Advanced Software Engineering", "CS", 530, true, 
					new Course("CS", 510)),
			
			new Course("Capstone in Software Engineering", "CS", 595, true, 
					new Course("CS", 501), 
					new Course("CS", 505), 
					new Course("CS", 506), 
					new Course("CS", 508), 
					new Course("CS", 510), 
					new Course("CS", 530)),
			
			
			
			new Course("Computing and Communications Technology", "CS", 502, false, 
					new Course("CS", 501)),
			
			new Course("Systems Programming", "CS", 355, false),
			
			new Course("Advanced Topics in Computer Science", "CS", 407, false, true),
			
			new Course("Computer Graphics", "CS", 423, false, 
					new Course("CS", 501)),
			
			new Course("Database Concepts", "CS", 460, false, 
					new Course("CS", 501)),
			
			new Course("Artificial Intelligence", "CS", 462, false, 
					new Course("CS", 501)),
			
			new Course("Algorithms", "CS", 463, false, 
					new Course("CS", 501)),
			
			new Course("Programming Languages", "CS", 464, false, 
					new Course("CS", 501)),
			
			new Course("Compiler Design", "CS", 465, false, 
					new Course("CS", 355)),
			
			new Course("Operating Systems Design", "CS", 481, false, 
					new Course("CS", 501)),
			
			new Course("Computer Communications Networks & Distributed Processing", "CS", 490, false, 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Computer Security", "CS", 492, false, 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Advanced Algorithms", "CS", 525, false, 
					new Course("CS", 501)),
			
			new Course("Topics in Human-Computer Interaction", "CS", 550, false, 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Introduction to Bioinformatics", "CS", 565, false, 
					new Course("CS", 501)),
			
			new Course("Topics in Artificial Intelligence", "CS", 570, false, 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Linked Data Engineering", "CS", 575, false, 
					new Course("CS", 505), 
					new Course("CS", 530)),
			
			new Course("Topics in Database Systems and Applications", "CS", 580, false, 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Topics in High Performance Computing and Communications", "CS", 590, false, 
					new Course("CS", 481), 
					new Course("CS", 501), 
					new Course("CS", 502)),
			
			new Course("Graduate Research Seminar", "CS", 594, false, 
					new Course("CS", 505), 
					new Course("CS", 506), 
					new Course("CS", 508), 
					new Course("CS", 530)),
			
			new Course("Topics in Software Engineering", "CS", 560, false, 
					new Course("CS", 510), 
					new Course("CS", 530)),
			
			
		};
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void GenerateGraph() {
		
		
	}

}
