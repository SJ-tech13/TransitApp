package com.tts.TransitApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.TransitApp.model.Bus;
import com.tts.TransitApp.service.BusRequest;
import com.tts.TransitApp.service.TransitService;

@Controller
public class TransitController {
    @Autowired
    private TransitService apiService;
	
    @GetMapping("/")
    public String getBusesPage(Model model){
        model.addAttribute("request", new BusRequest());
        return "index";
    }
	
    @PostMapping("/")
    public String getNearbyBuses(BusRequest request, Model model) {
        List<Bus> buses = apiService.getNearbyBuses(request);
        model.addAttribute("buses", buses);
        model.addAttribute("request", request);    
        return "index";
    }
    
    
    
}