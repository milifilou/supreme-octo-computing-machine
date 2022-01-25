import java.util.ArrayList;

/**
 * Student - Representation of a student.
 * 
 */

public class Student extends registeredPerson {
	protected static int MAX_NUM_MODULES = 5; // maximum number of modules that a student can enroll

	protected int numberModules; // number of modules currently enrolled
	protected ArrayList<Module> enrolledModules; // array of module codes for modules currently enrolled
	protected boolean isARUAA; // describes if a student has an ARUAA or not

	/**
	 * Constructor
	 * 
	 * @param fn Full Name
	 * @param rn Registration Number
	 * @param em Email
	 */
	public Student(String fn, int rn, String em) {
		super(fn, rn, em);
		this.numberModules = 0;
		this.isARUAA = false;
		this.enrolledModules = new ArrayList<Module>(MAX_NUM_MODULES);
	}

	/**
	 * Constructor
	 * 
	 * @param fn Full Name
	 * @param rn Registration Number
	 * @param em Email
	 * @param mo Enrolled Modules
	 * @param a  Whether Student has an ARUAA
	 */
	public Student(String fn, int rn, String em, ArrayList<Module> mo, boolean a) {
		super(fn, rn, em);
		this.enrolledModules = mo;
		enrolledModules.ensureCapacity(MAX_NUM_MODULES);
		this.isARUAA = a;
		this.numberModules = mo.size();
	}

	/**
	 * Note: Would have named the param for enrolled Modules em, if that were not
	 * email already.
	 */

	/**
	 * @return the Number of Modules
	 */
	public int getNumberModule() {
		return this.numberModules;
	}

	/**
	 * @return List of enrolled Modules
	 */
	public ArrayList<Module> getEnrolledModules() {
		return this.enrolledModules;
	}

	/**
	 * @param mo New list of enrolled Modules
	 */
	public void setEnrolledModules(ArrayList<Module> mo) {
		this.enrolledModules = mo;
	}

	/**
	 * @return If the student has an ARUAA
	 */
	public boolean isARUAA() {
		return this.isARUAA;
		/**
		 * Personal Note: If it were not contrary to instruction, would have named the
		 * attribute `ARUAA`, as that would not lead to isARUAA() returning isARUAA
		 */
	}

	/**
	 * @param a If the student has an ARUAA
	 */
	public void setARUAA(boolean a) {
		this.isARUAA = a;
	}

	/**
	 * @param mod - Module to add to this student
	 * @return true if the module was added successfully
	 */
	public boolean addModule(Module mod) {
		if (numberModules >= MAX_NUM_MODULES | enrolledModules.contains(mod)) {
			return false;
		}
		enrolledModules.add(mod);
		this.numberModules++;
		return true;

	}

	/**
	 * Removes a module from this student
	 * @param mod	The module to be removed
	 * @return		true if a module was removed successfully
	 */
	public boolean removeModule(Module mod) {
		if (enrolledModules.contains(mod)) {
			enrolledModules.remove(mod);
			numberModules--;
			return true;
		}
		return false;
	}

	public String toString() {
		return "Student: " + fullName;
	}

}
