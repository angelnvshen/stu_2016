package filter;

import java.math.BigDecimal;

/**
 * Created by CHANEL on 2016/4/7.
 */
public class Hours extends Salary {
    private int hours;
    private BigDecimal hoursSalary;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public BigDecimal getHoursSalary() {
        return hoursSalary;
    }

    public void setHoursSalary(BigDecimal hoursSalary) {
        this.hoursSalary = hoursSalary;
    }

    @Override
    public BigDecimal pay(){

        return new BigDecimal(hours).multiply(hoursSalary);
    }
}
