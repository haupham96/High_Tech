package chart_server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SaleReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "DATE")
    private String date;
    private String sale;
    private String invoice;

    public SaleReport() {
    }
}
