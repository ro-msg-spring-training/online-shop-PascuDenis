package ro.msg.learning.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Revenue implements Serializable {
    @Id
    @GeneratedValue
    private Integer revenueId;
    private Location location;
    private Date date;
    private Long sum;

    public Revenue() {
    }

    public Revenue(Integer revenueId, Location location, Date date, Long sum) {
        this.revenueId = revenueId;
        this.location = location;
        this.date = date;
        this.sum = sum;
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "revenueId=" + revenueId +
                ", location=" + location +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revenue revenue = (Revenue) o;
        return Objects.equals(revenueId, revenue.revenueId) &&
                Objects.equals(location, revenue.location) &&
                Objects.equals(date, revenue.date) &&
                Objects.equals(sum, revenue.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenueId, location, date, sum);
    }
}
