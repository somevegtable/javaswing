package entity;

public class Salary {
    private int id;

    private String name;

    /**
     * 基础薪资
     */
    private int basis;

    /**
     * 奖金
     */
    private int bonus;

    /**
     * 应发薪资
     */
    private int salary;

    /**
     * 扣除费用
     */
    private int deduct;

    /**
     * 实发薪资
     */
    private int realSalary;

    /**
     * 用户账号
     */
    private String account;

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

    public int getBasis() {
        return basis;
    }

    public void setBasis(int basis) {
        this.basis = basis;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDeduct() {
        return deduct;
    }

    public void setDeduct(int deduct) {
        this.deduct = deduct;
    }

    public int getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(int realSalary) {
        this.realSalary = realSalary;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basis=" + basis +
                ", bonus=" + bonus +
                ", salary=" + salary +
                ", deduct=" + deduct +
                ", realSalary=" + realSalary +
                ", account='" + account + '\'' +
                '}';
    }
}
