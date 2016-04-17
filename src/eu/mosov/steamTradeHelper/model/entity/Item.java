package eu.mosov.steamtradehelper.model.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Access(FIELD)
public class Item {
  @Id @GeneratedValue(strategy = AUTO)
  private Integer id;

  @NaturalId public String name;
  public String description;

  @ManyToMany(fetch = EAGER)
  @JoinTable(
      joinColumns = {@JoinColumn(name = "item_id")},
      inverseJoinColumns = {@JoinColumn(name = "quality_quality")}
  )
  public Set<Quality> qualities = new HashSet<>();

  protected Item() {
  }

  public Item(String name) {
    this.name = name;
  }

  public Item(Consumer<Item> builder) {
    builder.accept(this);
  }

  // methods
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Item)) return false;

    Item e = (Item) o;
    return name.toLowerCase().equals(e.getName().toLowerCase());
  }

  public int hashCode() {
    return name.toLowerCase().hashCode();
  }

  public String toString() {
    return name;
  }

  // getters & setters
  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setQualities(Set<Quality> qualities) {
    this.qualities = qualities;
  }

  public Set<Quality> getQualities() {
    return qualities;
  }
}
