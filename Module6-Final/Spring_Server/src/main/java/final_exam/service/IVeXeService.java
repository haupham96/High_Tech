package final_exam.service;


import final_exam.model.DoiTuong;
import final_exam.model.IReportDTO;
import final_exam.model.VeXe;

import java.util.List;

public interface IVeXeService {


    List<VeXe> findAll();

    VeXe findById(Integer id);

    void deleteById(Integer id);

    List<DoiTuong> findAllDoiTuong();

    VeXe findByMaTuyenXe(String maTuyenXe);

    void save(VeXe veXe);

    List<IReportDTO> getReports();
}
