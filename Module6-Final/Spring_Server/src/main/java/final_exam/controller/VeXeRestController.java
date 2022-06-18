package final_exam.controller;

import final_exam.model.IReportDTO;
import final_exam.model.VeXe;
import final_exam.service.IVeXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vexe")
public class VeXeRestController {

    @Autowired
    IVeXeService iVeXeService;

    @GetMapping("/list")
    public ResponseEntity<List<VeXe>> listVeXe() {
        List<VeXe> list = this.iVeXeService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVeXe(@PathVariable Integer id) {
        if (this.iVeXeService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.iVeXeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createVeXe(@RequestBody VeXe veXe) {

        if (this.iVeXeService.findByMaTuyenXe(veXe.getMaTuyenXe()) != null) {
            Map<String, String> mapErr = new HashMap<>();
            mapErr.put("maTuyenXe", "Mã tuyến xe đã tồn tại , vui lòng nhập lại .");
            return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);
        }
        this.iVeXeService.save(veXe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<List<IReportDTO>> getReport() {
        List<IReportDTO> reports = this.iVeXeService.getReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

}
