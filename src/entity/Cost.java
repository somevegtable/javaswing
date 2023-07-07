package entity;

public class Cost {

    private int id;

    private int amount;

    private String place;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", amount=" + amount +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
