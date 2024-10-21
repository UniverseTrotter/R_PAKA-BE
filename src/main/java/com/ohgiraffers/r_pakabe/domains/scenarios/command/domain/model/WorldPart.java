package com.ohgiraffers.r_pakabe.domains.scenarios.command.domain.model;


import jakarta.persistence.*;

@Entity
@Table
public class WorldPart {
    @Id
    @Column(name = "PART_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partId;

    @Column
    private String partName;

    @Column
    private boolean isPortalEnable;
}
