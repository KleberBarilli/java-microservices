package io.github.kleberbarilli.msicards.application.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kleberbarilli.msicards.application.domain.entities.ICard;

public interface ICardRepository extends JpaRepository<ICard, Long> {

}
