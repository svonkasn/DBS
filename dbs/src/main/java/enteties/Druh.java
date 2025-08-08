package enteties;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "druh")
public class Druh {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_druh", nullable = false)
  private Integer id;
//?????
//  @Column(name = "druh_masaz", nullable = false, length = 100)
//  private String druhMasaz;

  @ManyToMany(mappedBy = "druhy")
  private Set<Masaz> masaze = new HashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Masaz> getMasaze() {
    return masaze;
  }

  public void setMasaze(Set<Masaz> masaze) {
    this.masaze = masaze;
  }
}
