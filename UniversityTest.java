import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This UniversityTest class provides different methods of checking the
 * behaviour of the University class and the objects that it uses. It maintains
 * a reference to a University object called 'stirling' and populates it via the
 * loadData method.
 * 
 * If you run this class via a call to main, main will create a University
 * object and call the go method which produces some useful output that you can
 * analyse.
 * 
 * 
 */
public class UniversityTest {
	University stirling = new University();
	Student testJohn = new Student("John J", 147, "jj@stir.ac.uk");

	public static void main(String[] args) {
		UniversityTest test = new UniversityTest();
		test.go();
	}

	@Before
	public void loadData() {
		stirling.clear();

		Professor[] prof_list = new Professor[] { new Professor("Alek Sib", 159, "aleksib@stir.ac.uk", "Optimization"),
				new Professor("Jill Lai", 369, "jl@stir.ac.uk", "AI"),
				new Professor("Kevin Jack", 789, "kj@stir.ac.uk", "AI, Big Data"),
				new Professor("Guy F", 654, "gf@stir.ac.uk", "Machine and Deep Learning"),
				new Professor("Loius V", 643, "lv@stir.ac.uk", "Software Engineering"),
				new Professor("Lett A", 580, "la@stir.ac.uk", "Computer Vision"),
				new Professor("Stephanie A", 999, "sa@stir.ac.uk", "Data Science") };

		Student[] list = new Student[] { testJohn, new Student("Kate K", 565, "kk@stir.ac.uk"),
				new Student("Loius L", 6542, "ll@stir.ac.uk"), new Student("Zhang Z", 805, "zz@stir.ac.uk"),
				new Student("Xing L", 100, "xl@stir.ac.uk"), new Student("Alma A", 753, "aa@stir.ac.uk"),
				new Student("Borba B", 888, "bb@stir.ac.uk"), new Student("Zeus L", 001, "zl@stir.ac.uk"),
				new Student("Laura Q", 075, "lq@stir.ac.uk"), new Student("Pierre A", 130, "pa@stir.ac.uk"),
				new Student("Jess Z", 010, "jz@stir.ac.uk"), new Student("Hugh J", 101, "hj@stir.ac.uk"),
				new Student("Paul P", 654, "pp@stir.ac.uk"),
				new MScStudent("Felix C", 120, "fc@stir.ac.uk", "Fake News Detection", prof_list[6]),
				new MScStudent("Hannah S", 120, "hs@stir.ac.uk", "Penguin Recognition", prof_list[5]) };

		Module alg1 = new Module(147, "Algorithms 1");
		Module alg2 = new Module(199, "Algorithms 2");
		alg2.addPrerequisites(alg1);
		Module alg3 = new Module(201, "Algorithms 3");
		alg3.addPrerequisites(alg1);
		Module dst = new Module(250, "Data Structure");
		dst.addPrerequisites(alg1);
		dst.addPrerequisites(alg2);
		dst.addPrerequisites(alg3);
		Module ds = new Module(101, "Introduction to Data Science", new ArrayList<Module>(Arrays.asList(alg1)));
		Module ml = new Module(301, "Machine Learning");
		ml.addPrerequisites(alg1);
		ml.addPrerequisites(alg2);
		ml.addPrerequisites(alg3);
		ml.addPrerequisites(dst);
		Module dl = new Module(401, "Deep Learning");
		dl.addPrerequisites(alg1);
		dl.addPrerequisites(ml);
		ml.addPrerequisites(dl);
		Module nlp = new Module(450, "Natural Language Processing");
		nlp.addPrerequisites(ml);
		nlp.addPrerequisites(dl);
		nlp.addPrerequisites(ds);
		Module cn = new Module(475, "Computer Networks", new ArrayList<Module>(Arrays.asList(alg1)));
		Module g = new Module(501, "Computer Graphics", new ArrayList<Module>(Arrays.asList(alg2)));

		Cohort a1 = new Cohort(dl, new BinaryTree(new Student[] { list[0], list[1], list[2], list[5] }), prof_list[0]);
		stirling.addClass(a1);

		Cohort a2 = new Cohort(ml, new BinaryTree(new Student[] { list[3], list[4], list[6] }), prof_list[1]);
		stirling.addClass(a2);

		Cohort a3 = new Cohort(alg3, new BinaryTree(new Student[] { list[6], list[10], list[11], list[10] }),
				prof_list[2]);
		stirling.addClass(a3);

		Cohort a4 = new Cohort(ds, new BinaryTree(new Student[] { list[5], list[4], list[0], list[11], list[10] }),
				prof_list[3]);
		stirling.addClass(a4);

		Cohort a5 = new Cohort(dst, new BinaryTree(new Student[] { list[0], list[1], list[6], list[7], list[8] }),
				prof_list[4]);
		stirling.addClass(a5);

		Cohort a6 = new Cohort(alg2, new BinaryTree(new Student[] { list[2], list[5], list[6], list[9] }),
				prof_list[5]);
		stirling.addClass(a6);

		Cohort a7 = new Cohort(alg1, new BinaryTree(new Student[] { list[7], list[8], list[9], list[10], list[11] }),
				prof_list[6]);
		stirling.addClass(a7);

		Cohort a8 = new Cohort(g,
				new BinaryTree(new Student[] { list[0], list[5], list[11],
						new MScStudent("Jane G", 561, "jg@stir.ac.uk", "Deep Learning Image Segmentation",
								prof_list[6]),
						new MScStudent("Rose T", 300, "rt@stir.ac.uk", "Food Production", prof_list[5]) }),
				prof_list[0]);
		stirling.addClass(a8);

		Cohort a9 = new Cohort(cn,
				new BinaryTree(new Student[] {
						new MScStudent("Jack I", 200, "ji@stir.ac.uk", "Coronavirus Detection", prof_list[3]),
						new MScStudent("Mary M", 100, "mm@stir.ac.uk", "Sales Optimization", prof_list[2]) }),
				prof_list[2]);
		stirling.addClass(a9);
	}

	/**
	 * Print the contents of the ArrayList 'list' to standard output.
	 * 
	 * @param list The list to print
	 */
	public void printArrayList(ArrayList<Cohort> list) {
		for (Cohort v : list)
			System.out.println(v.toString());
	}

	/**
	 * 'go' produces a useful set of output that allows you to see the initial state
	 * of the modules arraylist, the results of doing the tree walk, an attempt at
	 * finding an object in the tree and the results of doing a merge sort and a
	 * binary search. Initially, only the state of the modules list will be correct.
	 * As you add further functionality, you should see the correct output being
	 * printed via this method. You do not need to modify it but you may want to add
	 * your own checks to confirm your code is working as intended.
	 */
	public void go() {
		loadData();
		System.out.println("--Module List--");
		stirling.describeModuleList();
		System.out.println();

		System.out.println("\n--Tree Walk--\n" + stirling.walkTree(0));

		System.out.println("\n--Find--");
		Student mf = stirling.find(0, "John J"); // should be found
		if (mf != null)
			System.out.println("Found: " + mf);
		else
			System.out.println("Could not find John J");

		Student aw = stirling.find(1, "Jack J"); // should not be found
		if (aw != null)
			System.out.println("Found: " + aw);
		else
			System.out.println("Could not find Jack J");

		System.out.println("\n--Sort asceding name--");
		ArrayList<Cohort> sorted;
		// Reload the data, otherwise it will still be sorted...
		loadData();
		sorted = stirling.quickSort(true, "name");
		printArrayList(sorted);

		System.out.println("\n--Sort desceding code--");
		// Reload the data, otherwise it will still be sorted...
		loadData();
		sorted = stirling.quickSort(false, "code");
		printArrayList(sorted);

		System.out.println("\n--Loop Search--");
		if (stirling.checkForCycles()) {
			System.out.println("Graph has cycle and checkForCycles returned TRUE");
		} else {
			System.out.println("Graph has cycle but checkForCycles returned FALSE");
		}
	}

	/**
	 * A test for the in-order walk
	 */
	@Test
	public void walkTest() {
		String expected = "John J,Alma A,Kate K,Loius L";
		String actual = stirling.walkTree(0);
		assertEquals("The tree walk was meant to walk: " + expected + ", but instead walked: " + actual, expected,
				actual);
		stirling.walkTree(0);
	}

	/**
	 * A test for the quickSort when sorting by name ascending
	 */
	@Test
	public void checkModuleEnrolment() {
		// All of these just create a pre-sorted expected list and compare to the sort
		// result
		ArrayList<Cohort> expected = new ArrayList<Cohort>();
		expected.add(stirling.getCohort().get(6));
		expected.add(stirling.getCohort().get(5));
		expected.add(stirling.getCohort().get(2));
		expected.add(stirling.getCohort().get(7));
		expected.add(stirling.getCohort().get(8));
		expected.add(stirling.getCohort().get(4));
		expected.add(stirling.getCohort().get(0));
		expected.add(stirling.getCohort().get(3));
		expected.add(stirling.getCohort().get(1));
		ArrayList<Cohort> actual = stirling.quickSort(true, "name");
		assertEquals("The quick sort did not result in names descending", expected, actual);
	}

	/**
	 * A test for the quickSort when sorting by name descending
	 */
	@Test
	public void checkModuleEnrolmentND() {
		ArrayList<Cohort> expected = new ArrayList<Cohort>();
		expected.add(stirling.getCohort().get(1));
		expected.add(stirling.getCohort().get(3));
		expected.add(stirling.getCohort().get(0));
		expected.add(stirling.getCohort().get(4));
		expected.add(stirling.getCohort().get(8));
		expected.add(stirling.getCohort().get(7));
		expected.add(stirling.getCohort().get(2));
		expected.add(stirling.getCohort().get(5));
		expected.add(stirling.getCohort().get(6));

		ArrayList<Cohort> actual = stirling.quickSort(false, "name");
		assertEquals("The quick sort did not result in names ascending", expected, actual);
	}

	/**
	 * A test for the quickSort when sorting by code ascending
	 */
	@Test
	public void checkModuleEnrolmentCA() {
		ArrayList<Cohort> expected = new ArrayList<Cohort>();
		expected.add(stirling.getCohort().get(3));
		expected.add(stirling.getCohort().get(6));
		expected.add(stirling.getCohort().get(5));
		expected.add(stirling.getCohort().get(2));
		expected.add(stirling.getCohort().get(4));
		expected.add(stirling.getCohort().get(1));
		expected.add(stirling.getCohort().get(0));
		expected.add(stirling.getCohort().get(8));
		expected.add(stirling.getCohort().get(7));
		ArrayList<Cohort> actual = stirling.quickSort(true, "code");
		assertEquals("The quick sort did not result in codes ascending" + actual, expected, actual);
	}

	/**
	 * A test for the quickSort when sorting by code descending
	 */
	@Test
	public void checkModuleEnrolmentCD() {
		ArrayList<Cohort> expected = new ArrayList<Cohort>();
		expected.add(stirling.getCohort().get(7));
		expected.add(stirling.getCohort().get(8));
		expected.add(stirling.getCohort().get(0));
		expected.add(stirling.getCohort().get(1));
		expected.add(stirling.getCohort().get(4));
		expected.add(stirling.getCohort().get(2));
		expected.add(stirling.getCohort().get(5));
		expected.add(stirling.getCohort().get(6));
		expected.add(stirling.getCohort().get(3));
		ArrayList<Cohort> actual = stirling.quickSort(false, "code");
		assertEquals("The quick sort did not result in codes descending", expected, actual);
	}

	/**
	 * A test for the find method
	 */
	@Test
	public void findTest() {
		//test john is the actual object tasked to be found
		Student jjExpected = testJohn;
		Student mf = stirling.find(0, "John J"); // should be found
		assertEquals("The find method could not find John J in Cohort 0, despite him being there.", jjExpected, mf);

	}

	/**
	 * A test for the find method when student not in cohort
	 */
	@Test
	public void findTest1() {
		Student aw = stirling.find(1, "Jack J");
		assertNull("The find method found a Jack J in Cohort 1, where he should not be:" + aw, aw);
	}

	/**
	 * A test for the loop Search 
	 */
	@Test
	public void loopSearchTest() {
		//Stirling is already cyclical, no need for set up
		assertTrue("Test Graph has cycle but checkForCycles returned FALSE", stirling.checkForCycles());
	}

	/**
	 * A test for the loop Search when there is no loop
	 */
	@Test
	public void loopSearchTest1() {
		// remove the edge that makes stirling cyclic
		stirling.getGraph().removeEdge(stirling.getGraph().getVertices().get(1),
				stirling.getGraph().getVertices().get(0));
		assertFalse("Test Graph has no cycle but checkForCycles returned TRUE", stirling.checkForCycles());
	}
}
