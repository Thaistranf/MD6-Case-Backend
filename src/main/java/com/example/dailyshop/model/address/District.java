package com.example.dailyshop.model.address;

import jakarta.persistence.*;
import lombok.*;

@Getter         //Ko tạo getter
@Setter         //Ko tạo Setter
@Data
@AllArgsConstructor  //Ko tạo constructor full tham số
@NoArgsConstructor   //Ko tạo constructor không tham số
@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String districtName;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
}
