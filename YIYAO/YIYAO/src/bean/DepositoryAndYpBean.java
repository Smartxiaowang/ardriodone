package bean;


import java.io.Serializable;

public class DepositoryAndYpBean implements Serializable {
    private final static long serialVersionUID = 1l;
    private int uId = 0;
    private String name;
    private String type;
    private String number;
    private String description;
    private String depository_id;
    private String depository_name;
    private String depository_readme;
    private String depository_yp;
    private String depository_arr;

    public String getDepository_id() {
        return depository_id;
    }

    public void setDepository_id(String depository_id) {
        this.depository_id = depository_id;
    }

    public String getDepository_name() {
        return depository_name;
    }

    public void setDepository_name(String depository_name) {
        this.depository_name = depository_name;
    }

    public String getDepository_readme() {
        return depository_readme;
    }

    public void setDepository_readme(String depository_readme) {
        this.depository_readme = depository_readme;
    }

    public String getDepository_yp() {
        return depository_yp;
    }

    public void setDepository_yp(String depository_yp) {
        this.depository_yp = depository_yp;
    }

    public String getDepository_arr() {
        return depository_arr;
    }

    public void setDepository_arr(String depository_arr) {
        this.depository_arr = depository_arr;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
