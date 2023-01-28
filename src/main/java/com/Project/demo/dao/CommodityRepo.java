package com.Project.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Commodity;

@Repository
public interface CommodityRepo extends JpaRepository<Commodity, Long> {

    List<Commodity> findByCommodityName(String commodityName);

    List<Commodity> findByCommodityType(String commodityType);

    List<Commodity> findByCommodityTypeAndCommodityName(String commodityType, String commodityName);

    Commodity findBySlNo(Long slNo);

}
