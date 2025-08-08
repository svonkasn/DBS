package enteties;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zamestnanec")
@PrimaryKeyJoinColumn(name = "id_osoba")
public class Zamestnanec extends Osoba{

  @Column(name = "rodne_cislo", nullable = false, length = 15, unique = true)
  private String rodneCislo;

  @Column(name = "ulice", nullable = false, length = 60)
  private String ulice;

  @Column(name = "cislo", nullable = false)
  private Integer cislo;

  @Column(name = "psc", nullable = false)
  private Integer psc;

  @ManyToMany(mappedBy = "zamestnanci")
  private Set<Masaz> masaze = new HashSet<>();

  public String getRodneCislo() {
    return rodneCislo;
  }

  public void setRodneCislo(String rodneCislo) {
    this.rodneCislo = rodneCislo;
  }

  public String getUlice() {
    return ulice;
  }

  public void setUlice(String ulice) {
    this.ulice = ulice;
  }

  public Integer getCislo() {
    return cislo;
  }

  public void setCislo(Integer cislo) {
    this.cislo = cislo;
  }

  public Integer getPsc() {
    return psc;
  }

  public void setPsc(Integer psc) {
    this.psc = psc;
  }

  public Set<Masaz> getMasaze() {
    return masaze;
  }

  public void setMasaze(Set<Masaz> masaze) {
    this.masaze = masaze;
  }
}
