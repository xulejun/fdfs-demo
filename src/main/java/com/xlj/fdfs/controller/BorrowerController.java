package com.xlj.fdfs.controller;

import com.xlj.fdfs.po.BorrowerPO;
import com.xlj.fdfs.service.BorrowerService;
import com.xlj.fdfs.util.FastDfsUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
     * @description 跳转到上传页面
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

    /**
     * @description 文件上传
     * @author xlj
     * @date 2020/11/26 20:52
     */
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
        BorrowerPO borrowerPo = new BorrowerPO();
        borrowerPo.setId(id)
                .setFileName(originalFilename)
                .setFileSize((int) myFile.getSize())
                .setGroupName(result[0])
                .setFilePath(result[1])
                .forInsert();
        borrowerService.update(borrowerPo);

        model.addAttribute("message", "文件上传成功，点击确定返回列表页面");
        model.addAttribute("url", "/");
        return "success";
    }

    /**
     * @description 文件下载
     * @author xlj
     * @date 2020/11/26 20:53
     */
    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) throws Exception {
        // 获取相应数据
        BorrowerPO borrowerPo = borrowerService.selectById(id);
        byte[] dataBytes = FastDfsUtil.download(borrowerPo.getGroupName(), borrowerPo.getFilePath());

        // 获取请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置响应类型为文件类型
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置响应时文件大小
        httpHeaders.setContentLength(borrowerPo.getFileSize());
        // 设置下载时默认的文件名——字符编码转换，防止默认文件名乱码
        httpHeaders.setContentDispositionFormData("attachment", new String(borrowerPo.getFileName().getBytes("UTF-8"),"ISO-8859-1"));

        /**
         * ResponseEntity 响应实体,响应时的具体数据可以是一段html代码、也可以是一段js、也可以是文件流
         * 参数1：body响应时的数据
         * 参数2：headers请求头
         * 参数3：status状态码
         */
        ResponseEntity responseEntity = new ResponseEntity<>(dataBytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    /**
    * @description 文件删除
    * @author xlj
    * @date 2020/11/26 22:00
    */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        borrowerService.deleteById(id);
        return "redirect:/";
    }

}
