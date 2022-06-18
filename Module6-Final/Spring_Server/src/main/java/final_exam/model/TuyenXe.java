package final_exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class TuyenXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String maTuyenXe;
    private String diaDiemKhoiHanh;
    private String diaDiemKetThuc;

    @Column(columnDefinition = "TIME")
    private String thoiGianKhoiHanh;
    private String thoiGianGianCach;
    private String loTrinh;

    @JsonBackReference
    @OneToMany(mappedBy = "tuyenXe")
    private Set<VeXe> veXe;

    public TuyenXe() {
    }

}
