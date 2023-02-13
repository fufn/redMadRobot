package com.bootcamp.testTask.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image")
    private Byte[] image;

    @ManyToOne
    private Users initializerUser;

    @ManyToOne
    private Users currentUserBuying;

}
