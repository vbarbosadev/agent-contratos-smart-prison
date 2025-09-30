package imd.urfn.br.emailagent;

import imd.urfn.br.emailagent.domain.*;
import imd.urfn.br.emailagent.dto.ContractRequestDTO;
import imd.urfn.br.emailagent.dto.ContractResponseDTO;
import imd.urfn.br.emailagent.dto.EmailRequestDTO;
import imd.urfn.br.emailagent.dto.IdResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.UUID;

@HttpExchange("/api")
public interface ApiCallInterface {

    @GetExchange("/contracts/get")
    String get();

    @PostExchange("/emails/send")
    String email(@RequestBody EmailRequestDTO email);

    @PostExchange("/contracts")
    void save(@RequestBody ContractRequestDTO contrato);
//
//    @PostExchange("/status")
//    void status(@RequestBody FinalCheckDTO contrato);

    @PutExchange("/contracts/{id}")
    String update(@RequestBody ContractRequestDTO contrato, Integer Id);

    @GetExchange("/contracts/{id}")
    ContractResponseDTO getContract(@PathVariable("id") UUID Id);

//
//    @GetExchange("/getContrato/{id}")
//    String getContrato(@PathVariable("id") String id);

}
