package com.lj.springcloud.micoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors
@Proxy(lazy = false)
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false ,length = 100)
    private String name;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false , length = 50 , name="database_name")
    private String databaseName;

}
