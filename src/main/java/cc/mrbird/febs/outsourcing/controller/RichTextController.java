package cc.mrbird.febs.outsourcing.controller;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.outsourcing.entity.Outsourcing;
import cc.mrbird.febs.outsourcing.mapper.OutsourcingMapper;
import cc.mrbird.febs.outsourcing.service.impl.OutsourcingServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author A stubborn man
 * @create 2021/2/26 15:57
 * @Description 富文本工具类
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class RichTextController {

    /**
     * layui照片保存及回调
     * */
    @RequestMapping(value="/uploadConImage",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadconimage(HttpServletRequest request, @RequestParam MultipartFile file) {
        Map<String,Object> mv=new HashMap<String, Object>();
        Map<String,String> mvv=new HashMap<String, String>();
        try {
            String rootPath = request.getSession().getServletContext().getRealPath("/image/");
            //Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //获得文件最初的路径
            String originalFile = file.getOriginalFilename();
            System.out.println("路径:"+originalFile);
            //UUID转化为String对象
            String uuid = UUID.randomUUID().toString();
            String newfilename=date.get(Calendar.YEAR)+""+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DATE)+uuid.replace("-", "")+originalFile;
            //得到完整路径名
            File newFile = new File(rootPath+newfilename);
            System.out.println("完整路径:"+newFile);
            /*文件不存在就创建*/
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            String filename=originalFile.substring(0, originalFile.indexOf("."));
            System.out.println(originalFile);
            System.out.println(filename);
            file.transferTo(newFile);
            System.out.println("newFile : "+newFile);
            String urlpat= "/image/"+ newfilename;
            mvv.put("src", urlpat);
            mvv.put("title",newfilename);
            mv.put("code", 0);
            mv.put("msg", "上传成功");
            mv.put("data", mvv);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.put("success", 1);
            return mv;
        }
    }

}
