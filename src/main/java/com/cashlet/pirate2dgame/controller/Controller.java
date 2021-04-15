package com.cashlet.pirate2dgame.controller;

import com.cashlet.pirate2dgame.model.Cell;
import com.cashlet.pirate2dgame.model.MapPostResponse;
import com.cashlet.pirate2dgame.model.PathFindResponse;
import com.cashlet.pirate2dgame.service.BusinessLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class Controller {
    @Autowired
    BusinessLogic businessLogic;

    @PostMapping("/map")
    public ResponseEntity<?> postMap(@RequestBody Cell[][] requestBody) throws IOException {
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();

        File file = Paths.get("Map.json").toFile();

        // convert map to JSON file
        mapper.writeValue(file, requestBody);

        if (file.length() > 0)
            return ResponseEntity.ok().body(new MapPostResponse("Successful"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MapPostResponse("Something went wrong"));
    }

    @GetMapping("/findPath")
    public ResponseEntity<?> getMapping(@RequestParam("startXPosition") int x0,
                                        @RequestParam("startYPosition") int y0,
                                        @RequestParam("targetXPosition") int xn,
                                        @RequestParam("targetYPosition") int yn) throws IOException {
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();

        File file = Paths.get("Map.json").toFile();

        // convert map to JSON file
        Cell[][] arrayOfArrays = mapper.readValue(file, Cell[][].class);
        PathFindResponse response = businessLogic.getMapping(arrayOfArrays, x0, y0, xn, yn);

        if (Objects.isNull(response))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MapPostResponse("A path was not found"));
        return ResponseEntity.ok().body(response);
//        return ResponseEntity.ok().build();
    }
}
