package com.example.fly4AviationApp.repositories;

import com.example.fly4AviationApp.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    @Query(value = "select * from cargo where flight_items_id =:flightItemsId", nativeQuery = true)
    List<Cargo> getAllByFlightItemsId(@Param("flightItemsId")int flightItemsIdByFlightId);
}
