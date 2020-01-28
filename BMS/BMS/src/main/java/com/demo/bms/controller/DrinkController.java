package com.demo.bms.controller;


import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.Drink;
import com.demo.bms.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/Drink")
public class DrinkController {
    @Autowired
    DrinkService service;

    @GetMapping("/AdminSelectDrink")
    public BaseModel AdminSelectDrinkInfo(String drink_name,int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectDrinkInfo(drink_name,curr_page,page_size,baseModel);
    }

    @GetMapping("/AdminSelectById")
    public BaseModel AdminSelectById(Integer drinkId, BaseModel baseModel)throws Exception {
        return service.AdminSelectById(drinkId, baseModel);
    }

    @PostMapping("/upload")
    public BaseModel upload(@RequestParam("file") MultipartFile file, Drink drink, HttpServletRequest request, BaseModel baseModel) throws Exception {
       // String path = request.getSession().getServletContext().getRealPath("/upload");
        String path = "D:/tool/HBuilderX/resource/Project2/static/upload";
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFilename = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFilename);

        //创建新名字
        String newFileName = UUID.randomUUID()+originalFilename;
        //在指定路径下创建一个文件
        File targetFile = new File(path, newFileName);
        file.transferTo(targetFile);
        drink.setDrinkPicture(newFileName);
        return service.upload(drink, baseModel);
    }

    @PostMapping("/AdminAddDrink")
    public BaseModel AdminAddDrink(Drink drink, BaseModel baseModel) throws Exception {
        drink.setCreateTimesd(new Date());
        drink.setIsDeleted(0);
        drink.setVersion(0);
        return service.AdminAddDrink(drink, baseModel);
    }

    @PostMapping("/AdminDeleteDrink")
    public BaseModel AdminDeleteDrink(Integer drinkId, Integer isDeleted, BaseModel baseModel) throws Exception {
        Drink drink = new Drink();
        if (isDeleted.equals(0)) {
            drink.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            drink.setIsDeleted(0);
        }
        return service.AdminDeleteDrink(drinkId, isDeleted, baseModel);
    }

    @PostMapping(value = "/deleteDrink/{ids}")
    public BaseModel AdminDeletedAllDrink(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllDrink(ids, baseModel);
    }

    @PostMapping(value = "/ChangeScore")
    public BaseModel ChangeScore(Integer commentId, String score, BaseModel baseModel) throws Exception {
        return service.ChangeScore(commentId, score, baseModel);
    }

    @PostMapping(value = "/AdminUpdateDrink")
    public BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception {
        drink.setUpdateTime(new Date());
        drink.setIsDeleted(0);
        return service.AdminUpdateDrink(drink, baseModel);
    }
}
