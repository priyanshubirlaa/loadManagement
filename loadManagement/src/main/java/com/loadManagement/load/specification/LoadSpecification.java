package com.loadManagement.load.specification;


import org.springframework.data.jpa.domain.Specification;

import com.loadManagement.load.entity.Load;

public class LoadSpecification {
    public static Specification<Load> hasShipperId(String shipperId) {
        return (root, query, criteriaBuilder) ->
                shipperId != null ? criteriaBuilder.equal(root.get("shipperId"), shipperId) : null;
    }

    public static Specification<Load> hasTruckType(String truckType) {
        return (root, query, criteriaBuilder) ->
                truckType != null ? criteriaBuilder.equal(root.get("truckType"), truckType) : null;
    }

    public static Specification<Load> hasProductType(String productType) {
        return (root, query, criteriaBuilder) ->
                productType != null ? criteriaBuilder.equal(root.get("productType"), productType) : null;
    }

    public static Specification<Load> hasLoadingPoint(String loadingPoint) {
        return (root, query, criteriaBuilder) ->
                loadingPoint != null ? criteriaBuilder.equal(root.get("facility").get("loadingPoint"), loadingPoint) : null;
    }

    public static Specification<Load> hasUnloadingPoint(String unloadingPoint) {
        return (root, query, criteriaBuilder) ->
                unloadingPoint != null ? criteriaBuilder.equal(root.get("facility").get("unloadingPoint"), unloadingPoint) : null;
    }
}
