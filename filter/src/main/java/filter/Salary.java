package filter;

import java.math.BigDecimal;

/**
 * Created by CHANEL on 2016/4/7.
 */
public class Salary {
    private String name;
    private BigDecimal salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    // 计算员工的工资
    public BigDecimal pay(){
        return salary;
    }
}
