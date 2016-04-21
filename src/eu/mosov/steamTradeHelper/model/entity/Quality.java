package eu.mosov.steamtradehelper.model.entity;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;

import static javax.persistence.AccessType.FIELD;

@Entity
@Access(FIELD)
public class Quality {

  @Id String quality;
  int gameID;

  protected Quality() {
  } // constructor for Hibernate

  public Quality(String quality) {
    this.quality = quality;
    this.gameID = -1; // cause 0 it's game id of 'Normal' quality
  }

  public Quality(String name, int gameID) {
    this.quality = name;
    this.gameID = gameID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Quality)) return false;

    Quality quality = (Quality) o;

    return this.quality != null && this.quality.equals(quality.quality);
  }

  @Override
  public int hashCode() {
    return quality != null ? quality.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "{" +
        "quality='" + quality + '\'' +
        ", gameID=" + gameID +
        '}';
  }

  // getters & setters
  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID = gameID;
  }
}
