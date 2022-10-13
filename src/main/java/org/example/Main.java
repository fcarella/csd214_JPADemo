package org.example;

import org.example.entities.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf=null;
        EntityManager em=null;

        try{
            emf= Persistence.createEntityManagerFactory("default");
            em=emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created ("+emf+")");
            em.getTransaction().begin();

            PersonEntity p=new PersonEntity();
            p.setFirstname("Thomas2");
            p.setLastname("Jefferson2");

            em.persist(p);

            em.getTransaction().commit();

//            em=emf.createEntityManager();
            List<PersonEntity> ListOfPersonEntitys = em.createQuery("SELECT c FROM PersonEntity c").getResultList();
            System.out.println("List of PersonEntitys");
            for(PersonEntity PersonEntity:ListOfPersonEntitys){
                System.out.println(PersonEntity.getFirstname()+" "+p.getLastname());
            }

        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(emf!=null)
                emf.close();
//            if(em!=null)
//                em.close();
        }
    }

}