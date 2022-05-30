package chart_server.repository;

import chart_server.model.SaleReport;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISaleReportRepository extends JpaRepository<SaleReport, Integer> {

    @Query(value = "select id , date , sale , invoice from demo.sale_report ; ",
    nativeQuery = true)
    <T> List<T> findAllSaleReport(Class<T> t);
}
