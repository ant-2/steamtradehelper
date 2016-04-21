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

  // Enum
  public enum Qualities {
    NORMAL("Normal", 0),
    GENUINE("Genuine", 1),
    VINTAGE("Vintage", 3),
    UNUSUAL("Unusual", 5),
    UNIQUE("Unique", 6),
    SELF_MADE("Self-Made", 9),
    STRANGE("Strange", 11),
    HAUNTED("Haunted", 13),
    COLLECTORS("Collector's", 14);

    Qualities(String name, Integer gameId) {
      this.name = name;
      this.gameId = gameId;
      this.quality = new Quality(name, gameId);
    }

    private final String name;
    private final Integer gameId;
    private final Quality quality;

    public String getQualityName() {
      return name;
    }

    public Integer getGameId() {
      return gameId;
    }

    public Quality getQuality() {
      return quality;
    }

    public static Quality getQualityByGameId(int gameId) {
      Qualities[] values = Qualities.values();
      for (Qualities q : values) {
        if (q.getGameId() == gameId) {
          return q.getQuality();
        }
      }
      return null;
    }

    public static Quality getQualityFromEnum(Qualities enumEntry) {
      return enumEntry.getQuality();
    }
  }
}
