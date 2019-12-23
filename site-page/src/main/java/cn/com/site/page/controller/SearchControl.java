package cn.com.site.page.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.site.page.util.AccessSourceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Maps;

import cn.com.site.page.dto.PageBean;
import cn.com.site.page.dto.SiteResDto;
import cn.com.site.page.service.SearchService;

/**
 * @author 作者 zhoubin
 * @version V1.0 Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @Description: TODO
 * @since JDK 1.8
 */
@Controller
public class SearchControl {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    @ResponseBody
    public PageBean<List<SiteResDto>> searchByQuery(@RequestParam("query") String query,
                                                    @RequestParam(name = "begin", defaultValue = "0") Integer begin,
                                                    @RequestParam(name = "limit", defaultValue = "15") Integer limit,
                                                    HttpServletRequest request) {
        PageBean<List<SiteResDto>> pageBean = new PageBean<>();
        int from = begin * pageBean.getSize();
        log.info("recive a requesr, params is : query = {}, begin = {}, limit = {}, from = {}", query, begin, limit, from);
        List<SiteResDto> list = searchService.searchByQuery(query, from, limit);
        log.warn("query is {}, result size is {}", query, list.size());

        AccessSourceLog.accessLog(request);
        pageBean.setBegin(begin);
        if (list.size() > 10) {
            list = list.subList(0, 10);
        }
        pageBean.setT(list);
        return pageBean;
    }

    @RequestMapping("/recent")
    @ResponseBody
    public List<SiteResDto> searchRecent(HttpServletRequest request) {
        log.info("get recent data");
        AccessSourceLog.accessLog(request);
        List<SiteResDto> list = searchService.searchRecent();
        log.warn("recent is {}, result size is {}", list.size());
        return list;
    }

    @RequestMapping("/target")
    public void sendToDest(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response,
                           Model model) throws IOException {
        log.info("send to dest is : {}", url);
        AccessSourceLog.accessLog(request);
        // url必须带上协议，才能成功跳转
        response.sendRedirect(url);
        // 下面代码仅做保留，提供第二中国跳转方式。
        // model.addAttribute("url", url);
        // return "/common/redirect";
    }
}
