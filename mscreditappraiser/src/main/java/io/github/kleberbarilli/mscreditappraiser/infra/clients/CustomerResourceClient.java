package io.github.kleberbarilli.mscreditappraiser.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerData;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> getCustomerData(@RequestParam("cpf") String cpf);

}
