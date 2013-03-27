package Chat;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/20/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account implements Serializable,Comparable {
    private String username,password;
    private int team;

    public Account(String username, String password,int team) {
        this.username = username;
        this.password = password;
        this.team=team;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return username;
    }

    @Override
    public int compareTo(Object o) {
        return username.compareTo(((Account)o).getUsername());
    }
}
