package com.ms.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"sample", "sam"})
public class SampleController {
    @GetMapping
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSampleGreeting() {
        return "Hello Sample Application...";
    }

    @GetMapping("/inside")
    public String getInsideSampleGreeting() {
        return "Hello Inside Sample Application...";
    }

    @GetMapping({"/outside", "/out"})
    public String getOutsideSampleGreeting() {
        return "Hello Outside Sample Application...";
    }
}
