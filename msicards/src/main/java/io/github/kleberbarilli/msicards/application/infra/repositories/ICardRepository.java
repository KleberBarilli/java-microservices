package io.github.kleberbarilli.msicards.application.infra.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kleberbarilli.msicards.application.domain.entities.ICard;

public interface ICardRepository extends JpaRepository<ICard, Long> {
    List<ICard> findByIncome(BigDecimal income);
}
