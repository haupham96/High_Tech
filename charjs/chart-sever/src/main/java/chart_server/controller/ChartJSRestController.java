package chart_server.controller;

import chart_server.model.ISaleReportDTO;
import chart_server.service.ISaleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ChartJSRestController {

    @Autowired
    ISaleReportService iSaleReportService;

    @GetMapping("")
    public ResponseEntity<List<ISaleReportDTO>> getAllSaleReport() {
        List<ISaleReportDTO> list = this.iSaleReportService.finAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
