package enteties;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "recenze", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"id_zakaznik", "datum"})
})public class Recenze {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_recenze", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "id_zakaznik", nullable = false)
  private enteties.Zakaznik idZakaznik;

  @Column(name = "datum", nullable = false)
  private LocalDateTime datum;

  @Column(name = "komentar", nullable = false)
  private String komentar;

  @Column(name = "hodnoceni", nullable = false)
  private int hodnoceni;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "id_zamestnanec")
  private enteties.Zamestnanec idZamestnanec;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public enteties.Zakaznik getIdZakaznik() {
    return idZakaznik;
  }

  public void setIdZakaznik(enteties.Zakaznik idZakaznik) {
    this.idZakaznik = idZakaznik;
  }

  public LocalDateTime getDatum() {
    return datum;
  }

  public void setDatum(LocalDateTime datum) {
    this.datum = datum;
  }

  public String getKomentar() {
    return komentar;
  }

  public void setKomentar(String komentar) {
    this.komentar = komentar;
  }

  public Integer getHodnoceni() {
    return hodnoceni;
  }

  public void setHodnoceni(Integer hodnoceni) {
    this.hodnoceni = hodnoceni;
  }

  public enteties.Zamestnanec getIdZamestnanec() {
    return idZamestnanec;
  }

  public void setIdZamestnanec(enteties.Zamestnanec idZamestnanec) {
    this.idZamestnanec = idZamestnanec;
  }

}
