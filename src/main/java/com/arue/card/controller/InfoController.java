package com.arue.card.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    @Value("${ebin.version}") //comes from application.properties
    private String ebinVersion;

    @GetMapping
    @RequestMapping("/version")
    public Map getGameVersion() {
        Map map = new HashMap<String, String>();
        map.put("ebin-card-game-version", ebinVersion);
        return map;
    }
}
