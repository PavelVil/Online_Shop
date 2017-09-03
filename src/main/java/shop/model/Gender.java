package shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long genderId;
    @Column(name = "name")
    private String genderName;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "gender", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public Gender() {
    }

    public Gender(Long id, String genderName) {
        this.genderId = id;
        this.genderName = genderName;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public enum GenderEnum{
        MEN, WOMEN
    }

    @Override
    public String toString() {
        return "Gender{" +
                "genderId=" + genderId +
                ", genderName='" + genderName + '\'' +
                '}';
    }
}
