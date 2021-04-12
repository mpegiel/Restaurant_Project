package pl.agh.restaurant_project;

public class Manager {
    private final int uniqueID;
    private String firstName;
    private String lastName;
    private String password; //TODO: encryption
    private String email;
    private String login;
    private int salary;

    public Manager(int uID0, String fName0, String lName0, String pswd0, String email0, String login0, int salary0) {
        uniqueID = uID0;
        firstName = fName0;
        lastName = lName0;
        password = pswd0;
        email = email0;
        login = login0;
        salary = salary0;
    }


    public int getUniqueID() {
        return uniqueID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
