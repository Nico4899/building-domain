package edu.kit.tm.cm.smartcampus.building.infrastructure.database;

import edu.kit.tm.cm.smartcampus.building.logic.model.Building;
import edu.kit.tm.cm.smartcampus.building.logic.model.BuildingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Primary
@Repository
public class BuildingDatabaseRepository implements BuildingRepository { //TODO bis jz nzur "copy paste" -> hier mit Persistent entitys usw arbeiten

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Building> findByBin(String bin) {
        return Optional.empty();
    }

    @Override
    public Collection<Building> findAll() {
        return null;
    }

    @Override
    public String create(Building building) {
        return null;
    }

    @Override
    public boolean delete(String bin) {
        return false;
    }

    @Override
    public boolean update(Building building) {
        return false;
    }


}
