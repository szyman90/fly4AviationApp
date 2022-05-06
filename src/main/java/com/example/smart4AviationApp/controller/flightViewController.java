package com.example.smart4AviationApp.controller;

import com.example.smart4AviationApp.model.Flight;
import com.example.smart4AviationApp.model.dto.FlightDto;
import com.example.smart4AviationApp.repositories.FlightRepository;
import com.example.smart4AviationApp.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flight/view")
public class flightViewController {
    private final FlightRepository flightRepository;
    private final FlightService flightService;

    public flightViewController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
    }

    @GetMapping("/list")
    public String showUserList(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flightTemplate";
    }
}
