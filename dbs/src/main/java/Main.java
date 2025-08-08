import DAO.BaseDAO;
import enteties.Masaz;
import enteties.Osoba;
import enteties.Zakaznik;
import enteties.Zamestnanec;

import java.time.LocalDateTime;


public class Main {
  public static void main(String[] args) throws Exception {
    try (BaseDAO dao = new BaseDAO()) {
      // insert
      Zakaznik novy = new Zakaznik();
      novy.setJmeno("Eva");
      novy.setPrijmeni("Kučerová");
      novy.setTelCislo("+420777888999");
      novy.setEmail("eva.kucerova@example.cz");
      dao.insert(novy);
      System.out.println("INSERT : zakaznik id = " + novy.getId());

      // UPDATE
      novy.setVipKarta("VIP123356");
      dao.update(novy);
      System.out.println("UPDATE : přidána VIP karta");

      // vypis potomku
      System.out.println("\nVšichni lidé (Osoba + dědicové):");
      for (Osoba o : dao.getAllPeople()) {
        System.out.printf("  - %s %s, tel: %s, typ=%s%n",
          o.getJmeno(), o.getPrijmeni(), o.getTelCislo(),
          o.getClass().getSimpleName());
      }
      // delete
      dao.delete(Zakaznik.class, novy.getId());
      System.out.println("DELETE : zakaznik id = " + novy.getId());

      System.out.println("\nVšichni zaměstnance:");
      for (Zamestnanec z : dao.findAllWorkersInfo()) {
        System.out.printf("  - %s %s, tel: %s%n",
          z.getJmeno(), z.getPrijmeni(), z.getTelCislo());
      }

      // TRANSAKCE
      Integer idZakaznik = 100;
      Integer idZamestnanec= 10;
      Integer idMasaz =  8;

      LocalDateTime od = LocalDateTime.of(2025, 6, 11, 15, 0);
      LocalDateTime do_ = od.plusMinutes(90);

      boolean ok = dao.reserveIfFree(
        idZakaznik,
        idZamestnanec,
        idMasaz,
        od,
        do_);

      System.out.println(
        ok ? "Rezervace vytvořena" : "Masér není volný"
      );

      boolean notOk = dao.reserveIfFree(
        idZakaznik,
        idZamestnanec,
        idMasaz,
        od,
        do_);

      System.out.println(
        notOk ? "Rezervace vytvořena" : "Masér není volný"
      );

      System.out.println("Masáže a jejich zaměstnanci:");
      for ( Masaz m : dao.findAllWithZamestnanci()) {
        System.out.print("  - " + m.getNazev() + " [");
        String sep = "";
        for (Zamestnanec z : m.getZamestnanci()) {
          System.out.print(sep + z.getJmeno() + " " + z.getPrijmeni());
          sep = ", ";
        }
        System.out.println("]");
      }

      System.out.println("\nZaměstnanci a jejich masáže:");
      for (Zamestnanec z : dao.findAllWithMasaze()) {
        System.out.print("  - " + z.getJmeno() + " " + z.getPrijmeni() + ": ");
        String sep = "";
        for (Masaz m : z.getMasaze()) {
          System.out.print(sep + m.getNazev());
          sep = ", ";
        }
        System.out.println();
      }
      int testId = 1;
      System.out.println("\nMasáže zaměstnance s ID " + testId + ":");
      for (Masaz m : dao.findByZamestnanecId(testId)) {
        System.out.println("  - " + m.getNazev());
      }

      System.out.println("\nSpojení masáží a zaměstnanců:");
      for (Object[] row : dao.findMasazZamestnanecNames()) {
        System.out.printf("  - Masáž: %s, Zaměstnanec: %s %s%n", row[0], row[1], row[2]);
      }
    }
  }
}
