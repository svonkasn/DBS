package enteties;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "objednavka", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"id_zakaznik", "datum_vytvoreni"})
})public class Objednavka {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_objednavka", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "id_zakaznik", nullable = false)
  private enteties.Zakaznik idZakaznik;

  @Column(name = "datum_vytvoreni", nullable = false)
  private LocalDateTime datumVytvoreni;

  @Column(name = "datum_splneni", nullable = false)
  private LocalDateTime datumSplneni;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "id_zamestnanec", nullable = false)
  private enteties.Zamestnanec idZamestnanec;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "id_masaz", nullable = false)
  private Masaz idMasaz;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Zakaznik getIdZakaznik() {
    return idZakaznik;
  }

  public void setIdZakaznik(Zakaznik idZakaznik) {
    this.idZakaznik = idZakaznik;
  }

  public LocalDateTime getDatumVytvoreni() {
    return datumVytvoreni;
  }

  public void setDatumVytvoreni(LocalDateTime datumVytvoreni) {
    this.datumVytvoreni = datumVytvoreni;
  }

  public LocalDateTime getDatumSplneni() {
    return datumSplneni;
  }

  public void setDatumSplneni(LocalDateTime datumSplneni) {
    this.datumSplneni = datumSplneni;
  }

  public Zamestnanec getIdZamestnanec() {
    return idZamestnanec;
  }

  public void setIdZamestnanec(Zamestnanec idZamestnanec) {
    this.idZamestnanec = idZamestnanec;
  }

  public Masaz getIdMasaz() {
    return idMasaz;
  }

  public void setIdMasaz(Masaz idMasaz) {
    this.idMasaz = idMasaz;
  }
}
