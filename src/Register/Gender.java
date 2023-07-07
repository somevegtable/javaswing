package Register;
public class Gender {
    private Object genger;

    public Gender() {
    }

    @Override
    public String toString() {
        return "Gender{" +
                "genger=" + genger +
                '}';
    }

    public Object getGenger() {
        return  genger;
    }

    public void setGenger(Object genger) {
        this.genger = genger;
    }

    public Gender(Object genger) {
        this.genger = genger;
    }
}

