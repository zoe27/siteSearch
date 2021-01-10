package cn.com.site.page.service;

import cn.com.site.page.dto.LevelMapping;
import cn.com.site.page.vo.EvaluateComp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: EvaluateLevelMapping
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:55
 */
public interface EvaluateLevelMappingService {

    EvaluateComp addEvaluateComp(EvaluateComp evaluateComp);

    List<EvaluateComp> getEvaluateComp(String companyName, Integer companyId);

    List<LevelMapping> evaluateLevel(LevelMapping levelMapping);
}
