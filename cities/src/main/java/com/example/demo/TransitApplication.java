package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * SpringApplication along with a simple REST controller
 * and a custom exception handler
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@Api(value = "Transit demo application", description = "Find if a path exists between two cities")
public class TransitApplication {

    private final Log LOG = LogFactory.getLog(getClass());

    @Autowired
    County county;

    public static void main(String[] args) {
        SpringApplication.run(TransitApplication.class, args);
    }

    @ApiOperation(value = "Find if a path exists between two cities",
            notes = "Returns true if cites connected and false otherwise ",
            response = String.class)
    @GetMapping(value = "/connected", produces = "text/plain")
    public ResponseEntity<String> isConnected(
            @ApiParam(name = "origin", value = "Origin City name", required = true) @RequestParam String origin,
            @ApiParam(name = "destination", value = "Destination City name", required = true) @RequestParam String destination) {

        if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination))
        return new ResponseEntity<>("Either destination or origin city is empty ",  HttpStatus.BAD_REQUEST);

        City originCity = county.getCity(origin.toUpperCase());
        City destCity = county.getCity(destination.toUpperCase());

        if(StringUtils.isEmpty(originCity) || StringUtils.isEmpty(destCity))
            return new ResponseEntity<>("Either destination or origin city does not exist or invalid", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(Commuter.commute(originCity, destCity) ? "yes" : "no", HttpStatus.OK);
    }

}
