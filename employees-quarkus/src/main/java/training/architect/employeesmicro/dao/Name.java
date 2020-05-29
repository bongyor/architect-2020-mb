package training.architect.employeesmicro.dao;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Name {

    private String forename;

    private String surename;

}
