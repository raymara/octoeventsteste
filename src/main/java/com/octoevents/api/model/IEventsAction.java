package com.octoevents.api.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@MappedSuperclass
public class IEventsAction implements IEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    protected Integer id;

    private Date dateCreated;
    
    @JsonIgnore
    private Date lastUpdated;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @PreUpdate
    @PrePersist
    public void timeStamps() {
        lastUpdated = new Date();
        if (dateCreated.equals(null)) {
            dateCreated = new Date();
        }
    }

    @Override
    @JsonInclude(Include.NON_NULL)
    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public Date getLastUpdated() {
        return lastUpdated;
    }

  
}
