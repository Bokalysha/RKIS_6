package ru.bokalysha.rkis.Prac6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bokalysha.rkis.Prac6.models.Smartphone;

import java.util.List;

/**
 * Репозиторий для работы со смартфонами.
 */
@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {

    /**
     * Поиск смартфона по цене, равной или большей заданной.
     *
     * @param price Цена смартфона, относительно которой производится поиск.
     * @return Список смартфонов, у которой цена равна или больше заданной.
     */
    List<Smartphone> findByPriceGreaterThanEqual(float price);
}
