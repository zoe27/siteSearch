package cn.com.site.page.service.impl;

import cn.com.site.page.dto.LevelMapping;
import cn.com.site.page.mapper.EvaluateCompMapper;
import cn.com.site.page.service.EvaluateLevelMappingService;
import cn.com.site.page.vo.EvaluateComp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: EvaluateLevelMappingServiceImpl
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:57
 */
@Component
public class EvaluateLevelMappingServiceImpl implements EvaluateLevelMappingService {

    @Autowired
    private EvaluateCompMapper evaluateCompMapper;

    @Override
    public EvaluateComp addEvaluateComp(EvaluateComp evaluateComp) {
        try{
            evaluateComp.setStatus(0);
            evaluateComp.setAddTime(new Date());
            evaluateCompMapper.insert(evaluateComp);
        }catch (Exception e){
            evaluateComp = null;
        }
        return evaluateComp;
    }

    @Override
    public List<EvaluateComp> getEvaluateComp(String companyName, Integer companyId) {
        List<EvaluateComp> list = evaluateCompMapper.selectByCompany(companyName, companyId);
        return list;
    }

    @Override
    public List<LevelMapping> evaluateLevel(LevelMapping levelMapping) {
        // to do
        // logic of analysis
        List<LevelMapping> list = null;
        if (StringUtils.isEmpty(levelMapping.getCompany())) {
            // compare all company

        } else {
            // compare one certain company
        }

        LevelMapping levelMapping1 = new LevelMapping();
        levelMapping1.setLevel("T5");
        levelMapping1.setCompany("百度");
        levelMapping1.setYearBase(300000d);

        LevelMapping levelMapping2 = new LevelMapping();
        levelMapping1.setLevel("T3.1");
        levelMapping1.setCompany("腾讯");
        levelMapping1.setYearBase(320000d);

        list.add(levelMapping2);
        list.add(levelMapping1);
        return list;
    }
}
