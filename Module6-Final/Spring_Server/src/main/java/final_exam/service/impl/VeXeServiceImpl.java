package final_exam.service.impl;

import final_exam.model.DoiTuong;
import final_exam.model.IReportDTO;
import final_exam.model.VeXe;
import final_exam.repository.IDoiTuongRepository;
import final_exam.repository.ILoaiTheReposiroty;
import final_exam.repository.ITuyenXeRepository;
import final_exam.repository.IVeXeRepository;
import final_exam.service.IVeXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeXeServiceImpl implements IVeXeService {

    @Autowired
    ILoaiTheReposiroty iLoaiTheReposiroty;

    @Autowired
    IDoiTuongRepository iDoiTuongRepository;

    @Autowired
    ITuyenXeRepository iTuyenXeRepository;

    @Autowired
    IVeXeRepository iVeXeRepository;

    @Override
    public List<VeXe> findAll() {
        return this.iVeXeRepository.findAll();
    }

    @Override
    public VeXe findById(Integer id) {
        return this.iVeXeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        this.iVeXeRepository.deleteById(id);
    }

    @Override
    public List<DoiTuong> findAllDoiTuong() {
        return this.iDoiTuongRepository.findAll();
    }

    @Override
    public VeXe findByMaTuyenXe(String maTuyenXe) {
        return this.iVeXeRepository.findByMaTuyenXe(maTuyenXe);
    }

    @Override
    public void save(VeXe veXe) {
        this.iVeXeRepository.save(veXe);
    }

    @Override
    public List<IReportDTO> getReports() {
        return this.iVeXeRepository.getReport(IReportDTO.class);
    }
}
