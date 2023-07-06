package entity;

public class In {

    private int id;

    private String name;

    private String gender;

    private String phone;

    private String fromplace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFromplace() {
        return fromplace;
    }

    public void setFromplace(String fromplace) {
        this.fromplace = fromplace;
    }

    @Override
    public String toString() {
        return "In{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", fromplace='" + fromplace + '\'' +
                '}';
    }
}
