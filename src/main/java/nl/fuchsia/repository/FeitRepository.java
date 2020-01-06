package nl.fuchsia.repository;

import nl.fuchsia.model.Feit;
import nl.fuchsia.model.Persoon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FeitRepository {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    private static final String GET_FEITEN ="SELECT feit FROM Feit feit";

    /**
     * Voegt een Feit toe aan de database
     * @param feit is het toe te voegen feit.
     */
    @Transactional
    public Feit addFeit(Feit feit) {
        entityManager.persist(feit);
        return feit;
    }

	/**
	 * Haalt het strafbare feit op basis van het feitnr.
	 *
	 * @param feitnr het feitnr van de op te halen feiten.
	 * @return het opgehaalde feit.
	 */
	@Transactional
    public Feit getFeitById(Integer feitnr){
    	return entityManager.find(Feit.class, feitnr);
	}

    /**
     * Haalt alle Feiten uit de database
     * @return een lijst met alle feiten
     */
    @Transactional
    public List<Feit> getFeiten() {
        TypedQuery<Feit> getAllFeiten = entityManager.createQuery(GET_FEITEN, Feit.class);
        return getAllFeiten.getResultList();
    }

    /**
     * wijzigd een bestaand feit.
     *
     * @param feit de te wijzigen feit.
     */
    @org.springframework.transaction.annotation.Transactional
    public Feit updateFeitById(Feit feit) {
        entityManager.merge(feit);
        return feit;
    }
}