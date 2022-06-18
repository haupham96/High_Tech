package final_exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class VeXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doi_tuong_id", referencedColumnName = "id")
    private DoiTuong doiTuong;

    @ManyToOne
    @JoinColumn(name = "tuyen_xe_id",referencedColumnName = "id")
    private TuyenXe tuyenXe ;

    private String maTuyenXe ;

    @Column(columnDefinition = "DATE")
    private String thoiGianSuDung;

    private String hoVaTen;
    @Column(columnDefinition = "DATE")
    private String ngaySinh;
    private String soDienThoai;
    private String email;
    private String ghiChu;
    private boolean status ;

    public VeXe() {
    }
}
