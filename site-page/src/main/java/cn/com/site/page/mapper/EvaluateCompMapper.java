package cn.com.site.page.mapper;

import cn.com.site.page.vo.EvaluateComp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateCompMapper {
    int insert(EvaluateComp record);

    List<EvaluateComp> selectAll();

    List<EvaluateComp> selectByCompany(@Param("companyName") String companyName, @Param("companyId") Integer companyId);
}