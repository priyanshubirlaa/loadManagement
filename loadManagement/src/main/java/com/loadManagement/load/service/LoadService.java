package com.loadManagement.load.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.loadManagement.load.entity.Load;
import com.loadManagement.load.exception.DatabaseException;
import com.loadManagement.load.exception.ResourceNotFoundException;
import com.loadManagement.load.repository.LoadRepository;
import com.loadManagement.load.specification.LoadSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public Load createLoad(Load load) {
        try {
            return loadRepository.save(load);
        } catch (Exception e) {
            throw new DatabaseException("Error saving load to database: " + e.getMessage());
        }
    }

    public List<Load> getFilteredLoads(String shipperId, String truckType, String productType, String loadingPoint, String unloadingPoint) {
        try {
            Specification<Load> spec = Specification.where(LoadSpecification.hasShipperId(shipperId))
                    .and(LoadSpecification.hasTruckType(truckType))
                    .and(LoadSpecification.hasProductType(productType))
                    .and(LoadSpecification.hasLoadingPoint(loadingPoint))
                    .and(LoadSpecification.hasUnloadingPoint(unloadingPoint));

            List<Load> loads = loadRepository.findAll(spec);
            if (loads.isEmpty()) {
                throw new ResourceNotFoundException("No loads found with the given filters.");
            }
            return loads;
        } catch (Exception e) {
            throw new DatabaseException("Error fetching loads: " + e.getMessage());
        }
    }

    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load with ID " + loadId + " not found."));
    }

    public Load updateLoad(UUID loadId, Load updatedLoad) {
        return loadRepository.findById(loadId).map(load -> {
            try {
                load.setFacility(updatedLoad.getFacility());
                load.setProductType(updatedLoad.getProductType());
                load.setTruckType(updatedLoad.getTruckType());
                load.setNoOfTrucks(updatedLoad.getNoOfTrucks());
                load.setWeight(updatedLoad.getWeight());
                load.setComment(updatedLoad.getComment());
                load.setShipperId(updatedLoad.getShipperId());
                load.setDate(updatedLoad.getDate());
                return loadRepository.save(load);
            } catch (Exception e) {
                throw new DatabaseException("Error updating load: " + e.getMessage());
            }
        }).orElseThrow(() -> new ResourceNotFoundException("Load with ID " + loadId + " not found."));
    }

    public void deleteLoad(UUID loadId) {
        if (!loadRepository.existsById(loadId)) {
            throw new ResourceNotFoundException("Load with ID " + loadId + " not found.");
        }
        try {
            loadRepository.deleteById(loadId);
        } catch (Exception e) {
            throw new DatabaseException("Error deleting load: " + e.getMessage());
        }
    }
}
