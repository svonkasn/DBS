import entities.Osoba;
import entities.Zakaznik;

import java.time.LocalDateTime;
import java.util.List;

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

            // update
            novy.setVipKarta("VIP-123456");
            dao.update(novy);
            System.out.println("UPDATE : přidána VIP karta");

            // delete
            dao.delete(Zakaznik.class, novy.getId());
            System.out.println("DELETE : zakaznik id = " + novy.getId());

            // vypis potomku
            System.out.println("\nVšichni lidé (Osoba + dědicové):");
            for (Osoba o : dao.getAllPeople()) {
                System.out.printf("  - %s %s, tel: %s, typ=%s%n",
                        o.getJmeno(), o.getPrijmeni(), o.getTelCislo(),
                        o.getClass().getSimpleName());
            }

            // transakce
            Integer idZakaznik   = 100;
            Integer idZamestnanec= 10;
            Integer idMasaz      =  8;

            LocalDateTime od  = LocalDateTime.of(2025, 6, 11, 14, 0);
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
        }
    }
}
