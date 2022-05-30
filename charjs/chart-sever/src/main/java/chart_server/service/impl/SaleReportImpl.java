package chart_server.service.impl;

import chart_server.model.ISaleReportDTO;
import chart_server.repository.ISaleReportRepository;
import chart_server.service.ISaleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleReportImpl implements ISaleReportService {

    @Autowired
    ISaleReportRepository iSaleReportRepository;

    @Override
    public List<ISaleReportDTO> finAll() {
        return this.iSaleReportRepository.findAllSaleReport(ISaleReportDTO.class);
    }
}
