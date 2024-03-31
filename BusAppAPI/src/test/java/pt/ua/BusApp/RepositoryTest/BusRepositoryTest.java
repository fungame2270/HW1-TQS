package pt.ua.BusApp.RepositoryTest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pt.ua.BusApp.Domain.Bus;
import pt.ua.BusApp.Repository.BusRepository;

@DataJpaTest
public class BusRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BusRepository busRepository;

    @Test
    public void whenSaveBus_andGetByIdThanGetBus(){
        Bus bus = new Bus();
        bus.setName("Avolta");
        bus.setCapacity(86);

        //entityManager.persistAndFlush(bus);
        busRepository.save(bus);
        Optional<Bus> entity = busRepository.findById(bus.getId());

        assertThat(entity.get().getName(), equalTo(bus.getName()));
        assertThat(entity.get().getCapacity(), equalTo(bus.getCapacity()));
    }

    @Test
    public void whenDeleteBusNotFound(){
        Bus bus = new Bus();
        bus.setName("Avolt");
        bus.setCapacity(86);
        entityManager.persistAndFlush(bus);

        busRepository.delete(bus);

        Optional<Bus> entity = busRepository.findById(bus.getId());

        assertTrue(!entity.isPresent());
    }

    @Test
    public void findAllReturnAll(){
        Bus bus = new Bus("avolta",78);
        Bus bus2 = new Bus("desVolta",97);
        Bus bus3 = new Bus("simVolta",86);

        entityManager.persist(bus);
        entityManager.persist(bus2);
        entityManager.persist(bus3);
        entityManager.flush();
        List<Bus> buses = busRepository.findAll();

        assertThat(buses).hasSize(3).extracting(Bus::getName).containsOnly(bus.getName(),bus2.getName(),bus3.getName());
    }
    
}
