package enteties;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zakaznik")
@PrimaryKeyJoinColumn(name = "id_osoba")
public class Zakaznik extends Osoba{

  @Column(name = "vip_karta", length = 10)
  private String vipKarta;

  @ManyToMany
  @JoinTable(
    name = "Doporucil",
    joinColumns = @JoinColumn(name = "id_zakaznik"),
    inverseJoinColumns = @JoinColumn(name = "id_doporuceny")
  )
  private Set<Zakaznik> doporuceneOsoby = new HashSet<>();

  @ManyToMany(mappedBy = "doporuceneOsoby")
  private Set<Zakaznik> doporucenoOd = new HashSet<>();

  public String getVipKarta() {
    return vipKarta;
  }

  public void setVipKarta(String vipKarta) {
    this.vipKarta = vipKarta;
  }

  public Set<Zakaznik> getDoporuceneOsoby() {
    return doporuceneOsoby;
  }

  public void setDoporuceneOsoby(Set<Zakaznik> doporuceneOsoby) {
    this.doporuceneOsoby = doporuceneOsoby;
  }

  public Set<Zakaznik> getDoporucenoOd() {
    return doporucenoOd;
  }

  public void setDoporucenoOd(Set<Zakaznik> doporucenoOd) {
    this.doporucenoOd = doporucenoOd;
  }
}
