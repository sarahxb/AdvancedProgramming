
/**
 * Represents a person with a name and a date of birth.
 */

public class Person {
    /** The name of the person */
    protected String name;

    /** The date of birth of the person YYYY-MM-DD  */
    protected String dob;

    /**
     * Constructs a Person with a name and date of birth.
     *
     * @param name the name of the person
     * @param dob  the date of birth of the person
     */

  public Person(String name, String dob) {
      this.name = name;
      this.dob = dob;
  }

    /**
     * Gets the date of birth of the person.
     *
     * @return the date of birth
     */
    public String getDob() {
        return this.dob;
    }
    /**
     * Gets the name of the person.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
