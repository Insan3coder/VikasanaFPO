package com.Project.demo.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Project.demo.dao.CommodityRepo;
import com.Project.demo.dto.CommodityDto;
import com.Project.demo.model.Commodity;

@Service
public class CommodityServices {

    @Autowired
    private CommodityRepo commodityRepo;

    private Logger logger = LogManager.getLogger(EventService.class);

    @Transactional(readOnly = true)
    public List<CommodityDto> getAllCommodity(Long slNo, String commodityName, String commodityType) {
        try {
            if (!Objects.isNull(commodityName) && Objects.isNull(commodityType) && Objects.isNull(slNo))
                return commodityRepo.findByCommodityName(commodityName).stream().map(x -> assignCommodityDto(x))
                        .collect(Collectors.toList());
            else if (!Objects.isNull(slNo))
                return commodityRepo.findById(slNo).stream().map(x -> assignCommodityDto(x))
                        .collect(Collectors.toList());
            else if (!Objects.isNull(commodityType) && Objects.isNull(commodityName) && Objects.isNull(slNo))
                return commodityRepo.findByCommodityType(commodityType).stream().map(x -> assignCommodityDto(x))
                        .collect(Collectors.toList());
            else if (!Objects.isNull(commodityType) && !Objects.isNull(commodityName))
                return commodityRepo.findByCommodityTypeAndCommodityName(commodityType, commodityName).stream()
                        .map(x -> assignCommodityDto(x)).collect(Collectors.toList());
            else
                return commodityRepo.findAll().stream()
                        .map(x -> assignCommodityDto(x)).collect(Collectors.toList());

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    private CommodityDto assignCommodityDto(Commodity commodity) {
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setSlNo(commodity.getSlNo());
        commodityDto.setCommodityName(commodity.getCommodityName());
        commodityDto.setCommodityType(commodity.getCommodityType());
        commodityDto.setPrice(commodity.getPrice());
        commodityDto.setQuantity(commodity.getQuantity());
        return commodityDto;
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public boolean createCommodity(CommodityDto commodity) {
        try {
            Commodity commodityDB = new Commodity();
            Commodity c = commodityRepo.findBySlNo(commodity.getSlNo());
            if (Objects.isNull(c)) {
                commodityDB.setCommodityName(commodity.getCommodityName());
                commodityDB.setCommodityType(commodity.getCommodityType());
                commodityDB.setPrice(commodity.getPrice());
                commodityDB.setQuantity(commodity.getQuantity());
                commodityDB.setSlNo(commodity.getSlNo());
                commodityRepo.save(commodityDB);
                return true;
            } else
                return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void updateCommodity(Long sl_no, CommodityDto commodity) {
        try {
            Commodity commodityDb = commodityRepo.findById(sl_no).get();
            if (!Objects.isNull(commodityDb)) {
                if (!Objects.isNull(commodity.getCommodityName()))
                    commodityDb.setCommodityName(commodity.getCommodityName());
                if (!Objects.isNull(commodity.getCommodityType()))
                    commodityDb.setCommodityType(commodity.getCommodityType());
                if (!Objects.isNull(commodity.getPrice()))
                    commodityDb.setPrice(commodity.getPrice());
                if (!Objects.isNull(commodity.getQuantity()))
                    commodityDb.setQuantity(commodity.getQuantity());
                if (!Objects.isNull(commodity.getSlNo()))
                    commodityDb.setSlNo(commodity.getSlNo());
                commodityRepo.save(commodityDb);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void deleteCommodity(Long sl_no) {
        try {
            commodityRepo.deleteById(sl_no);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

}