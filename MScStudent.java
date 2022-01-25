import java.util.ArrayList;

/**
 * MScStudent - Representation of a student working on a MSc.
 * 
 */
public class MScStudent extends Student {
	protected String researchTitle;
	protected Professor supervisor;

	/**
	 * Constructor
	 * 
	 * @param fn Full Name
	 * @param rn Registration Number
	 * @param em Email
	 * @param rt Research Title
	 * @param sv Supervisor
	 */
	public MScStudent(String fn, int rn, String em, String rt, Professor sv) {
		super(fn, rn, em);
		researchTitle = rt;
		supervisor = sv;
	}

	/**
	 * Constructor
	 * 
	 * @param fn Full Name
	 * @param rn Registration Number
	 * @param em Email
	 * @param mo Enrolled Modules
	 * @param a  Whether Student has an ARUAA
	 * @param rt Research Title
	 * @param sv Supervisor
	 */
	public MScStudent(String fn, int rn, String em, ArrayList<Module> mo, boolean a, String rt, Professor sv) {
		super(fn, rn, em, mo, a);
		researchTitle = rt;
		supervisor = sv;
	}
	/**
	 * @return the Research Title 
	 */
	public String getResearchTitle() {
		return this.researchTitle;
	}

	/**
	 * Set/change the research Title
	 * @param rt The new Research Title
	 */
	public void setResearchTitle(String rt) {
		this.researchTitle = rt;
	}

	/**
	 * 
	 * @return the Supervisor
	 */
	public Professor getSupervisor() {
		return this.supervisor;
	}

	/**
	 * Set/change the Supervisor
	 * @param sv The new Supervisor
	 */
	public void setSupervisor(Professor sv) {
		this.supervisor = sv;
	}

	
	public void print() {
		System.out.println(this.toString());
		System.out.println("Registration Number: " + this.getRegistrationNumber());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Enrolled Modules: " + this.getEnrolledModules().toString());
		System.out.println("ARUAA: " + this.isARUAA());
		System.out.println("Research Title: " + this.getResearchTitle());
		System.out.println("Supervisor: " + this.getSupervisor().toString());
	}
}
