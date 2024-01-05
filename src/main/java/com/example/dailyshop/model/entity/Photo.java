package com.example.dailyshop.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoID;
    @Column(nullable = false)
    private String photoName;

}
