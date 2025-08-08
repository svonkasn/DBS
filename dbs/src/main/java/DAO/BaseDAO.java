package DAO;

import enteties.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Closeable;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

public class BaseDAO implements Closeable {

  private final EntityManagerFactory emf;
  private final EntityManager em;

  public BaseDAO() {
    this.emf = Persistence.createEntityManagerFactory("default");
    this.em  = emf.createEntityManager();
  }

  // insert
  public <T> T insert(T entity) {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(entity);
    tx.commit();
    return entity;
  }

  // update
  public <T> T update(T entity) {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    T merged = em.merge(entity);
    tx.commit();
    return merged;
  }

  // delete
  public <T> void delete(Class<T> type, Object id) {
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    Optional.ofNullable(em.find(type, id)).ifPresent(em::remove);
    tx.commit();
  }

     /*
     Dědičnost
     Výpis všech potomků (Zakaznik + Zamestnanec) včetně
     polí předka (Osoba).  Vrací se společný
     super-typ Osoba kvůli jednoduchosti.
     */

  public List<Osoba> getAllPeople() {
    return em.createQuery("""
            SELECT o FROM Osoba o
            """, Osoba.class)
      .getResultList();
  }

  public List<Zamestnanec> findAllWorkersInfo() {
    return em.createQuery("""
            SELECT z FROM Zamestnanec z
            """, Zamestnanec.class)
      .getResultList();
  }

  // transakce
  public boolean reserveIfFree(Integer zakId,
                               Integer zamId,
                               Integer masazId,
                               LocalDateTime od,
                               LocalDateTime _do) {

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    try {
      // isolation = SERIALIZABLE
      em.unwrap(org.hibernate.Session.class)
        .doWork(conn -> conn.setTransactionIsolation(
          java.sql.Connection.TRANSACTION_SERIALIZABLE));

      boolean free = em.createQuery("""
        SELECT COUNT(o) FROM Objednavka o
        WHERE o.idZamestnanec.id = :zam
          AND o.datumSplneni >= :od
          AND o.datumSplneni <  :do
        """, Long.class)
        .setParameter("zam", zamId)
        .setParameter("od",  od)
        .setParameter("do",  _do)
        .getSingleResult() == 0;


      if (!free) {
        tx.rollback();
        return false;
      }

      Objednavka obj = new Objednavka();
      obj.setIdZakaznik(em.getReference(Zakaznik.class, zakId) );
      obj.setIdZamestnanec(em.getReference(Zamestnanec.class,zamId) );
      obj.setIdMasaz(em.getReference(Masaz.class, masazId) );
      obj.setDatumVytvoreni(LocalDateTime.now());
      obj.setDatumSplneni(od);

      em.persist(obj);
      tx.commit();
      return true;

    } catch (RuntimeException ex) {
      if (tx.isActive()) tx.rollback();
      throw ex;
    }
  }

  // Masaze
  public List<Masaz> findAllWithZamestnanci() {
    return em.createQuery(
      "SELECT DISTINCT m FROM Masaz m LEFT JOIN m.zamestnanci", Masaz.class
    ).getResultList();
  }
  public List<Masaz> findByZamestnanecId(int id) {
    return em.createQuery(
      "SELECT m FROM Masaz m JOIN m.zamestnanci z WHERE z.id = :id", Masaz.class
    ).setParameter("id", id).getResultList();
  }
  public List<Object[]> findMasazZamestnanecNames() {
    return em.createQuery(
      "SELECT m.nazev, z.jmeno, z.prijmeni FROM Masaz m JOIN m.zamestnanci z", Object[].class
    ).getResultList();
  }

  //Zamestnance
  public List<Zamestnanec> findAllWithMasaze() {
    return em.createQuery(
      "SELECT DISTINCT z FROM Zamestnanec z LEFT JOIN z.masaze", Zamestnanec.class
    ).getResultList();
  }

  @Override public void close() throws IOException {
    em.close(); emf.close();
  }

}
