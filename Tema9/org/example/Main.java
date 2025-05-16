package org.example;

import org.example.model.ContinentD;
import org.example.model.CountryD;
import org.example.repository.AbstractRepository;
import org.example.repository.ContinentDRepository;
import org.example.repository.ContinentRepository;
import org.example.repository.CountryDRepository;
import org.example.repository.CountryRepository;
import org.example.utility.JPAUtil;
import org.example.utility.LoggerConfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerConfig.getLogger(); // Initialize the logger

        // Obtain an EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        // Initialize repositories
        ContinentDRepository continentRepo = new ContinentDRepository(em);
        CountryDRepository countryRepo = new CountryDRepository(em);
        AbstractRepository<ContinentD, Integer> continentRepository = new ContinentRepository(em);
        AbstractRepository<CountryD, Integer> countryRepository = new CountryRepository(em);

        try {
            // Test ContinentDRepository functionality
            System.out.println("Testing ContinentD Repository...");
            ContinentD europe = new ContinentD("Europe");
            continentRepo.create(europe); // Create a new continent
            logger.info("Persisted Continent (using ContinentDRepository): " + europe);

            ContinentD foundContinent = continentRepo.findById(europe.getId()); // Find by ID
            System.out.println("Found Continent by ID: " + foundContinent);
            logger.info("Found Continent (using ContinentDRepository): " + foundContinent);

            List<ContinentD> continents = continentRepo.findByName("%Europe%"); // Find by name pattern
            System.out.println("Found Continents by Name: " + continents);
            logger.info("Found Continents by Name: " + continents);

            // Test CountryDRepository functionality
            System.out.println("\nTesting CountryD Repository...");
            CountryD france = new CountryD("France", "FR", europe);
            countryRepo.create(france); // Create a new country
            logger.info("Persisted Country (using CountryDRepository): " + france);

            CountryD foundCountry = countryRepo.findById(france.getId()); // Find by ID
            System.out.println("Found Country by ID: " + foundCountry);
            logger.info("Found Country (using CountryDRepository): " + foundCountry);

            List<CountryD> countries = countryRepo.findByName("%France%"); // Find by name pattern
            System.out.println("Found Countries by Name: " + countries);
            logger.info("Found Countries by Name: " + countries);

            // Test AbstractRepository functionality with persist
            logger.info("\nTesting persist operation (using AbstractRepository)...");
            continentRepository.persist(europe);
            logger.info("Persisted Continent: " + europe);

            countryRepository.persist(france);
            logger.info("Persisted Country: " + france);

            // Test AbstractRepository functionality with findById
            logger.info("\nTesting findById operation (using AbstractRepository)...");
            ContinentD abstractFoundContinent = continentRepository.findById(ContinentD.class, europe.getId());
            logger.info("Found Continent: " + abstractFoundContinent);

            CountryD abstractFoundCountry = countryRepository.findById(CountryD.class, france.getId());
            logger.info("Found Country: " + abstractFoundCountry);

            // Test AbstractRepository functionality with update
            logger.info("\nTesting update operation (using AbstractRepository)...");
            abstractFoundContinent.setName("Updated Europe");
            continentRepository.update(abstractFoundContinent);
            logger.info("Updated Continent: " + abstractFoundContinent);

            abstractFoundCountry.setName("Updated France");
            countryRepository.update(abstractFoundCountry);
            logger.info("Updated Country: " + abstractFoundCountry);

            // Test AbstractRepository functionality with delete
            logger.info("\nTesting delete operation (using AbstractRepository)...");
            countryRepository.delete(CountryD.class, abstractFoundCountry.getId());
            logger.info("Deleted Country with ID: " + abstractFoundCountry.getId());

            continentRepository.delete(ContinentD.class, abstractFoundContinent.getId());
            logger.info("Deleted Continent with ID: " + abstractFoundContinent.getId());

        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the EntityManager and EntityManagerFactory
            em.close();
            emf.close();
            JPAUtil.getInstance().closeEntityManagerFactory();
        }
    }
}