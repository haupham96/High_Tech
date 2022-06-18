package final_exam.controller;

import final_exam.model.DoiTuong;
import final_exam.service.IVeXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doituong")
public class DoiTuongRestController {
    @Autowired
    IVeXeService iVeXeService;

    @GetMapping("")
    public ResponseEntity<DoiTuong> getDoiTuong() {
        List<DoiTuong> list = this.iVeXeService.findAllDoiTuong();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list.get(0), HttpStatus.OK);
    }
}
