package lz.business.clothesManager.controller;

import lz.business.clothesManager.service.ClothesSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/stockManagerController")
public class StockManagerController {

    @RequestMapping("/stockIndex")
    public String stockIndex(HttpServletRequest request){
        return  "/yznz/stockManager/stockIndex";
    }
}
