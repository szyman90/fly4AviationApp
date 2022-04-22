package com.example.fly4AviationApp.repositories;

import com.example.fly4AviationApp.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    @Query(value = "select * from baggage where flight_items_id =:flightItemsId", nativeQuery = true)
    List<Baggage> getAllByFlightItemsId(@Param("flightItemsId")int flightItemsIdByFlightId);
    // List<Baggage> getBaggageByFlight
}

