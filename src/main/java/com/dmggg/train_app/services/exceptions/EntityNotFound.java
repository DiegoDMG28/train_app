package com.dmggg.train_app.services.exceptions;

public class EntityNotFound extends RuntimeException {
  public EntityNotFound(String msg) {
    super(msg);
  }
}
