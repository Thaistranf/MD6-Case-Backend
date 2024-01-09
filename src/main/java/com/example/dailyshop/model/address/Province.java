package com.example.dailyshop.model.address;

import jakarta.persistence.*;
import lombok.*;

@Getter         //Ko tạo getter
@Setter         //Ko tạo Setter
@Data
@AllArgsConstructor  //Ko tạo constructor full tham số
@NoArgsConstructor   //Ko tạo constructor không tham số
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provinceName;
}
