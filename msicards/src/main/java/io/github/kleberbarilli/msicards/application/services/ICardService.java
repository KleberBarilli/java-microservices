package io.github.kleberbarilli.msicards.application.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.kleberbarilli.msicards.application.domain.entities.ICard;
import io.github.kleberbarilli.msicards.application.infra.repositories.ICardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ICardService {

    private final ICardRepository repository;

    @Transactional
    public ICard save(ICard iCard) {
        return repository.save(iCard);
    }

    public List<ICard> getCardsFromIncome(Long income) {
        BigDecimal userIncome = BigDecimal.valueOf(income);
        return repository.findByIncome(userIncome);
    }

}
