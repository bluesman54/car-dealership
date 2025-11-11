package com.example.car_dealership.repository;

import com.example.car_dealership.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByManagerId(Long managerId);
    List<Deal> findByClientId(Long clientId);

    @Query("SELECT d FROM Deal d WHERE d.dealDate BETWEEN :start AND :end")
    List<Deal> findByDealDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
