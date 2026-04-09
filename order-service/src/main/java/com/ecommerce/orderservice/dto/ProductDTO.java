package com.ecommerce.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

public record ProductDTO(Long id,
                        //String name,
                        //Double price,
                        Integer stock
                        //private Category category;
                        //private String imageUrl;
                        //private String imagePublicId;
                        //private String description;
        )
{
}
