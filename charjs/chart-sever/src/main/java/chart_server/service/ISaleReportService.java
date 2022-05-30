package chart_server.service;

import chart_server.model.ISaleReportDTO;

import java.util.List;

public interface ISaleReportService {
    List<ISaleReportDTO> finAll();
}
