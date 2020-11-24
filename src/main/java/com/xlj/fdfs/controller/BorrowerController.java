package com.xlj.fdfs.controller;

import com.xlj.fdfs.po.BorrowerPo;
import com.xlj.fdfs.service.BorrowerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xlj
 * @date 2020/11/24 21:22
 */
@Controller
public class BorrowerController {
    @Resource
    private BorrowerService borrowerService;

    @RequestMapping("/")
    public ModelAndView borrowers() {
        ModelAndView modelAndView = new ModelAndView();
        List<BorrowerPo> borrowerPos = borrowerService.selectAll();
        modelAndView.addObject("borrowers", borrowerPos);
        modelAndView.setViewName("borrowers");
        return modelAndView;
    }

    @GetMapping("/upload/{id}")
    public ModelAndView toUpload(@PathVariable(value = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        BorrowerPo borrowerPo = borrowerService.selectById(id);
        modelAndView.addObject("borrower", borrowerPo);
        modelAndView.setViewName("upload");
        return modelAndView;
    }

}
