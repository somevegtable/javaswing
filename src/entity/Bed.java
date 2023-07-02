package entity;

public class Bed {

    private int id;

    private String code;

    private String status;

    private String holder;

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", holder='" + holder + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
