package com.example.dckr_db.repo;

import com.example.dckr_db.entity.JsbtInfo;
import com.example.dckr_db.entity.JsbtData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JsbtInfoRepository extends JpaRepository<JsbtInfo, UUID> {

    List<JsbtInfo> findAll();

    Page<JsbtInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsbtInfo t WHERE date(information_date)=:sana")
    List<JsbtInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JsbtInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JsbtInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsbtData d WHERE d.jshshir=:jshshir")
    List<JsbtData> findByJSHSHIR(@Param("jshshir") String jshshir);


    @Query(value = "select \n" +
            "coalesce(max(substr(jshshir, 11, 3)), '0')\n" +
            "from jsbt_data d\n" +
            "where " +
            "    left(jshshir, 1)=:index_pola_i_veka_rojdeniya " +
            "and substr(jshshir, 2, 6)=:data_rojdeniya_ddMMyy " +
            "and substr(jshshir, 8, 3)=:kod_rayona_goroda " +
//            "and substr(jshshir, 11, 3)=:poryadkobiy_nomer_grajdanina " +
//            "and right(jshshir, 1)=:kontrolnaya_ssifra " +
            "", nativeQuery = true)
    String genKodGraj(
            @Param("index_pola_i_veka_rojdeniya") String index_pola_i_veka_rojdeniya,
            @Param("data_rojdeniya_ddMMyy") String data_rojdeniya_ddMMyy,
            @Param("kod_rayona_goroda") String kod_rayona_goroda
    );
}
