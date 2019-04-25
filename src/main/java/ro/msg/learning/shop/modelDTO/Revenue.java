package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Data
@Entity(name = "Revenue")
@Table(name = "Revenue")
public class Revenue implements Serializable {
    @Id
    private Integer revenueId;
//    @ManyToOne()
//    @JoinColumn(name = "LocationID", insertable = false, updatable = false)
    private Integer locationId;
    private Date date;
    private Long sum;

    public Revenue() {
    }

    public Revenue(Integer revenueId, Integer location, Date date, Long sum) {
        this.revenueId = revenueId;
        this.locationId = location;
        this.date = date;
        this.sum = sum;
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Integer getLocation() {
        return locationId;
    }

    public void setLocation(Integer location) {
        this.locationId = location;
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
                ", locationId=" + locationId +
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
                Objects.equals(locationId, revenue.locationId) &&
                Objects.equals(date, revenue.date) &&
                Objects.equals(sum, revenue.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenueId, locationId, date, sum);
    }
}
