package com.abbedev.komtraffic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResultRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String start;
    private String destination;
    private float distance;
    private float duration;
    private String delay;
}
