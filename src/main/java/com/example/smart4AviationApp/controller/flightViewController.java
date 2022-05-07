package com.example.smart4AviationApp.controller;

import com.example.smart4AviationApp.model.dto.FlightDto;
import com.example.smart4AviationApp.model.dto.FlightViewDto;
import com.example.smart4AviationApp.repositories.FlightRepository;
import com.example.smart4AviationApp.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/flight/view")
public class flightViewController {
    private final FlightRepository flightRepository;
    private final FlightService flightService;

    public flightViewController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
    }

    @GetMapping("/createFlight")
    public String showCreateFlightForm(Model model) {
        model.addAttribute("flightViewDto", new FlightViewDto());
        return "createFlight";
    }

    @PostMapping("/createFlight")
    public String doCreateUser(@Valid @ModelAttribute("flightViewDto") FlightViewDto formData,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "createFlight";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(formData.getDepartureDate(), formatter);
        FlightDto flightDto = new FlightDto(formData.getFlightNumber(), formData.getDepartureAirportIATACode()
        ,formData.getArrivalAirportIATACode(), Timestamp.valueOf(dateTime));

        flightService.createNewFlight(flightDto);

        return "redirect:/flight/view/list";
    }

    @GetMapping("/list")
    public String showUserList(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flightTemplate";
    }
}
