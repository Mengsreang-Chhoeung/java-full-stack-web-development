package com.ms.spring_security_jwt.modules.app.controller;

import com.ms.spring_security_jwt.infrastructure.property.AppProperty;
import com.ms.spring_security_jwt.util.StaticBeanUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class AppController {
    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        AppProperty appProperty = StaticBeanUtil.getAppProperty();
        Map<String, Object> data = new HashMap<>();
        data.put("name", appProperty.getAppName());
        data.put("description", appProperty.getAppDescription());
        data.put("version", appProperty.getAppVersion());
        data.put("author", "mengsreang_chhoeung");
        data.put("api-docs", appProperty.getAppOriginUrl() + "/api-docs");

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = {"/api-doc", "/api-docs"})
    public RedirectView apiDocs() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
