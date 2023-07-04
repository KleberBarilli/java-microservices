package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppraiserResponse {
    private List<ApprovedCard> cards;
}
