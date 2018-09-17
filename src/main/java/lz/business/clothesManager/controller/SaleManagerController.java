package lz.business.clothesManager.controller;

import lz.business.clothesManager.service.ClothesSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/saleManagerController")
public class SaleManagerController {
    @Autowired
    ClothesSaleService clothesSaleService;
    @RequestMapping("/saleIndex")
    public String saleIndex(HttpServletRequest request){
        return  "/yznz/saleManager/saleIndex";
    }
}
