package Server.Entity;

public class Efficiency {
    private Integer id;
    private Integer employee_id;
    private Integer count_hour;
    private boolean reprimand;

    public Efficiency(Integer id, Integer employee_id, Integer count_hour, boolean reprimand) {
        this.id = id;
        this.employee_id = employee_id;
        this.count_hour = count_hour;
        this.reprimand = reprimand;
    }

    public Efficiency(Integer employee_id, Integer count_hour, boolean reprimand) {
        this.employee_id = employee_id;
        this.count_hour = count_hour;
        this.reprimand = reprimand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getCount_hour() {
        return count_hour;
    }

    public void setCount_hour(Integer count_hour) {
        this.count_hour = count_hour;
    }

    public boolean isReprimand() {
        return reprimand;
    }

    public void setReprimand(boolean reprimand) {
        this.reprimand = reprimand;
    }

    @Override
    public String toString() {
        return "Efficiency{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", count_hour=" + count_hour +
                ", reprimand=" + reprimand +
                '}';
    }
}
