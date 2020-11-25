package com.xlj.fdfs.controller;

import com.xlj.fdfs.po.BorrowerPO;
import com.xlj.fdfs.service.BorrowerService;
import com.xlj.fdfs.util.FastDfsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author xlj
 * @date 2020/11/24 21:22
 */
@Controller
public class BorrowerController {
    @Resource
    private BorrowerService borrowerService;

    /**
    * @description 借款人信息
    * @author xlj
    * @date 2020/11/25 22:29
    */
    @RequestMapping("/")
    public ModelAndView borrowers() {
        ModelAndView modelAndView = new ModelAndView();
        List<BorrowerPO> borrowerPos = borrowerService.selectAll();
        modelAndView.addObject("borrowers", borrowerPos);
        modelAndView.setViewName("borrowers");
        return modelAndView;
    }

    /**
    * @description
    * @author xlj
    * @date 2020/11/25 22:30
    */
    @GetMapping("/upload/{id}")
    public ModelAndView toUpload(@PathVariable(value = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        BorrowerPO borrowerPo = borrowerService.selectById(id);
        modelAndView.addObject("borrower", borrowerPo);
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @PostMapping("/upload")
    public String upload(MultipartFile myFile, Integer id, Model model) throws IOException {
        byte[] fileBytes = myFile.getBytes();
        // 获取文件名
        String originalFilename = myFile.getOriginalFilename();
        // 可能部分文件没有文件扩展名
        String fileExtName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        // 文件上传
        String[] result = FastDfsUtil.upload(fileBytes, fileExtName);

        // 更新借款人信息
        BorrowerPO borrowerPO = new BorrowerPO();
        borrowerPO.setId(id)
                .setFileName(originalFilename)
                .setFileSize((int) myFile.getSize())
                .setGroupName(result[0])
                .setFilePath(result[1])
                .forInsert();
        borrowerService.update(borrowerPO);

        model.addAttribute("message","文件上传成功，点击确定返回列表页面");
        model.addAttribute("url","/");
        return "success";
    }
}
