package model;

public class Usedata {
    private  Integer id;
    private  String Name;
    private  String password;

    public Usedata(Integer id, String name, String password) {
        this.id = id;
        Name = name;
        this.password = password;
    }

    public Usedata(String name, String password) {
        Name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
