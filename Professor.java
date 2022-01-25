/**
 * Professor - Representation of a professor.
 * 
 */
public class Professor extends registeredPerson {

	private String researchTopics;

	public Professor(String fn, int rn, String em, String rt) {
		super(fn, rn, em);
		this.researchTopics = rt;
	}

	/**
	 * @return the researchTopics
	 */
	public String getResearchTopics() {
		return researchTopics;
	}

	/**
	 * Set/change the researchTopics
	 * 
	 * @param em The new researchTopics
	 */
	public void setResearchTopics(String rt) {
		this.researchTopics = rt;
	}

	public String toString() {
		return "Professor: " + fullName;
	}
}
