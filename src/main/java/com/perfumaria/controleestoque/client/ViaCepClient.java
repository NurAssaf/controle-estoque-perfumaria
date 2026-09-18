package com.perfumaria.controleestoque.client;

import com.perfumaria.controleestoque.dto.ViaCepRespostaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepRespostaDTO consultarCep(@PathVariable("cep") String cep);
}