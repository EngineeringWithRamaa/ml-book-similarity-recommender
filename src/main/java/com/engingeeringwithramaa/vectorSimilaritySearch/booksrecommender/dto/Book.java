package com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.dto;

import java.math.BigDecimal;

public record Book(
        String name,
        String itemType,
        BigDecimal price,
        String currency,
        String category,
        String language,
        String createdBy,
        String publisher,
        String availableAsFormat,
        String itemAvailabilityStatus,
        int noOfPages,
        Object vector
) {};