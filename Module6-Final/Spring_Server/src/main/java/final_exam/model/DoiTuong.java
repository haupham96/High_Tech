package final_exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class DoiTuong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price ;

    @ManyToOne
    @JoinColumn(name = "loai_the_id", referencedColumnName = "id")
    private LoaiThe loaiThe;

    @JsonBackReference
    @OneToMany(mappedBy = "doiTuong")
    private Set<VeXe> veXes ;

    public DoiTuong() {
    }

}
