package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "Supplier")
@Table(name = "Supplier")
public class Supplier implements Serializable {
    @Id
    private Integer supplierId;
    private String name;

    public Supplier() {
    }

    public Supplier(Integer supplierId, String name) {
        this.supplierId = supplierId;
        this.name = name;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
