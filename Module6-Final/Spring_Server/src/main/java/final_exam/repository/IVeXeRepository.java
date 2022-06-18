package final_exam.repository;

import final_exam.model.VeXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVeXeRepository extends JpaRepository<VeXe, Integer> {

    VeXe findByMaTuyenXe(String maTuyenXe);

    @Query(value = "SELECT doi_tuong.name as doiTuong , loai_the.name as loaiVe , sum(doi_tuong.price) as tongTienVe , count(ve_xe.id) as soLuongVe \n" +
            "FROM loai_the \n" +
            "JOIN doi_tuong ON loai_the.id = doi_tuong.loai_the_id \n" +
            "JOIN ve_xe on ve_xe.doi_tuong_id = doi_tuong.id\n" +
            "GROUP BY loai_the.id " +
            "ORDER BY  sum(doi_tuong.price) ; ",
    nativeQuery = true)
    <T> List<T> getReport(Class<T> t);
}
