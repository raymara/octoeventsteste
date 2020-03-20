package com.octoevents.api.model;

import java.util.Date;

public interface IEvents {

    public Integer getId();

    void setId(Integer id);

    public Date getDateCreated();

    public Date getLastUpdated();
}
