package enteties;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "osoba", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"jmeno", "prijmeni", "tel_cislo"})
})public class Osoba {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_osoba")
  private Integer id_osoba;

  @Column(name = "jmeno", nullable = false, length = 60)
  private String jmeno;

  @Column(name = "prijmeni", nullable = false, length = 60)
  private String prijmeni;

  @Column(name = "tel_cislo", nullable = false, length = 20)
  private String telCislo;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  public Integer getId() {
    return id_osoba;
  }

  public void setId(Integer id) {
    this.id_osoba = id;
  }

  public String getJmeno() {
    return jmeno;
  }

  public void setJmeno(String jmeno) {
    this.jmeno = jmeno;
  }

  public String getPrijmeni() {
    return prijmeni;
  }

  public void setPrijmeni(String prijmeni) {
    this.prijmeni = prijmeni;
  }

  public String getTelCislo() {
    return telCislo;
  }

  public void setTelCislo(String telCislo) {
    this.telCislo = telCislo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
