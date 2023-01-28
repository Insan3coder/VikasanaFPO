package com.Project.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.Project.demo.Service.CommodityServices;
import com.Project.demo.dto.CommodityDto;

@Component
@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityServices commodityService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<CommodityDto> getCommodity(@RequestParam(value = "slNo", required = false) Long slNo,
            @RequestParam(value = "commodityType", required = false) String commodityType,
            @RequestParam(value = "CommodityName", required = false) String commodityName) {
        return commodityService.getAllCommodity(slNo, commodityName, commodityType);
    }

    @PostMapping
    public ResponseEntity<Object> createCommodity(@RequestBody CommodityDto commodity) {
        boolean status = commodityService.createCommodity(commodity);
        if (status)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Given SL No already exists", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{sl_no}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCommodity(@PathVariable(value = "sl_no") Long sl_no, @RequestBody CommodityDto commodity) {
        commodityService.updateCommodity(sl_no, commodity);
    }

    @DeleteMapping("/{sl_no}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCommodity(@PathVariable(value = "sl_no") Long sl_no) {
        commodityService.deleteCommodity(sl_no);
    }
}
