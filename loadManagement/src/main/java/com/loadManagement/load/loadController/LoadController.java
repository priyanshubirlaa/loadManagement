package com.loadManagement.load.loadController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.loadManagement.load.entity.Load;
import com.loadManagement.load.service.LoadService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @Operation(summary = "Create a new Load")
    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load) {
        return ResponseEntity.status(201).body(loadService.createLoad(load));
    }

    @Operation(summary = "Get Loads with Filters")
    @GetMapping
    public ResponseEntity<List<Load>> getLoads(
        @RequestParam(name = "shipperId", required = false) String shipperId,
        @RequestParam(name = "truckType", required = false) String truckType,
        @RequestParam(name = "productType", required = false) String productType,
        @RequestParam(name = "loadingPoint", required = false) String loadingPoint,
        @RequestParam(name = "unloadingPoint", required = false) String unloadingPoint) {
        
        return ResponseEntity.ok(loadService.getFilteredLoads(shipperId, truckType, productType, loadingPoint, unloadingPoint));
    }

    @Operation(summary = "Get Load by ID")
    @GetMapping("/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable("loadId") String loadId) {
        try {
            UUID uuid = UUID.fromString(loadId); // Convert String to UUID
            Load load = loadService.getLoadById(uuid);
            return ResponseEntity.ok(load);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + loadId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching Load with ID: " + loadId);
        }
    }

    @Operation(summary = "Update Load by ID")
    @PutMapping("/{loadId}")
    public ResponseEntity<?> updateLoad(@PathVariable("loadId") UUID loadId, @RequestBody Load updatedLoad) {
        try {
            Load load = loadService.updateLoad(loadId, updatedLoad);
            return ResponseEntity.ok(load);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + loadId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating Load with ID: " + loadId);
        }
    }

    @Operation(summary = "Delete Load by ID")
    @DeleteMapping("/{loadId}")
    public ResponseEntity<?> deleteLoad(@PathVariable("loadId") UUID loadId) {
        try {
            loadService.deleteLoad(loadId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + loadId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting Load with ID: " + loadId);
        }
    }
}
