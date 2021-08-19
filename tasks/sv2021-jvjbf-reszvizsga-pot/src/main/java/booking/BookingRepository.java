package booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE LOWER(a.city) LIKE LOWER(:city)")
    List<Accommodation> findByCityEquals(String city);
}
