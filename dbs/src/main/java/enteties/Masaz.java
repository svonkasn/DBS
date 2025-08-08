package enteties;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "masaz")
public class Masaz {

  public enum DelkaTrvani {
    _30, _45, _60, _90
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_masaz", nullable = false)
  private Integer id;

  @Column(name = "nazev", nullable = false, length = 100, unique = true)
  private String nazev;

  @Column(name = "cena", nullable = false)
  private Integer cena;

  @Convert(converter = DelkaTrvaniConverter.class)
  @Column(name = "delka_trvani", nullable = false)
  private DelkaTrvani delkaTrvani;


  @ManyToMany
  @JoinTable(name = "Zamestnanec_Dela_Masaz",
    joinColumns = @JoinColumn(name = "id_masaz"),
    inverseJoinColumns = @JoinColumn(name = "id_osoba"))
  private Set<Zamestnanec> zamestnanci = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "Druh_masaze",
    joinColumns = @JoinColumn(name = "id_masaz"),
    inverseJoinColumns = @JoinColumn(name = "id_druh"))
  private Set<Druh> druhy = new HashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNazev() {
    return nazev;
  }

  public void setNazev(String nazev) {
    this.nazev = nazev;
  }

  public Integer getCena() {
    return cena;
  }

  public void setCena(Integer cena) {
    this.cena = cena;
  }

  public DelkaTrvani getDelkaTrvani() {
    return delkaTrvani;
  }

  public void setDelkaTrvani(DelkaTrvani delkaTrvani) {
    this.delkaTrvani = delkaTrvani;
  }

  public Set<Zamestnanec> getZamestnanci() {
    return zamestnanci;
  }

  public void setZamestnanci(Set<Zamestnanec> zamestnanci) {
    this.zamestnanci = zamestnanci;
  }

  public Set<Druh> getDruhy() {
    return druhy;
  }

  public void setDruhy(Set<Druh> druhy) {
    this.druhy = druhy;
  }
}
