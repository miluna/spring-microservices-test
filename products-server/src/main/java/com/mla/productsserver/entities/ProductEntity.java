package com.mla.productsserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product")
public class ProductEntity {

    @Column(name = "idProduct")
    @GeneratedValue
    private Long id;

    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
}
