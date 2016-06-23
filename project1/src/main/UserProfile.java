package main;

/**
 * Created by Агент on 24.08.15.
 */
public class UserProfile {
    private String name;
    private String password;
    private String email;

    public UserProfile(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserProfile(String name, String password ) {
        this.name = name;
        this.password = password;
        this.email = null;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
