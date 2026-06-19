package com.dmggg.train_app.dtos.agroupament;

import com.dmggg.train_app.entities.agroupament.SubAgroupament;

public class SubAgroupamentSummaryResponse {
  private long id;
  private String name;

  public SubAgroupamentSummaryResponse() {}

  public SubAgroupamentSummaryResponse(SubAgroupament entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
