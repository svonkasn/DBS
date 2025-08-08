package enteties;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "oficialni_stranka")
public class OficialniStranka {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_oficialni_stranka", nullable = false)
  private Integer id;

  @Column(name = "url", nullable = false, length = 250)
  private String url;

  @Column(name = "foto", nullable = false, length = 100)
  private String foto;

  @Column(name = "vzdelani", nullable = false, length = Integer.MAX_VALUE)
  private String vzdelani;
  @OneToOne
  @JoinColumn(name = "id_osoba", nullable = false, unique = true)
  private Zamestnanec zamestnanec;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getVzdelani() {
    return vzdelani;
  }

  public void setVzdelani(String vzdelani) {
    this.vzdelani = vzdelani;
  }

  public Zamestnanec getZamestnanec() {
    return zamestnanec;
  }

  public void setZamestnanec(Zamestnanec zamestnanec) {
    this.zamestnanec = zamestnanec;
  }
}
