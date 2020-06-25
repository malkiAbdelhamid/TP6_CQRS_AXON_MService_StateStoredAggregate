package com.esi.mscommandformation.api;

import com.esi.coreapi.command.AddEtudiantCommand;
import com.esi.coreapi.command.CreateFormationCommand;
import com.esi.coreapi.command.RemoveEtudiantCommand;
import com.esi.coreapi.command.UpdateEtudiantCommand;
import com.esi.coreapi.dto.etudiantDTO;
import com.esi.coreapi.dto.formationDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("command")
public class ApiController {

    @Autowired
    CommandGateway commandGateway;

@PostMapping("/formation")
public String newFormation(@RequestBody formationDTO formationDTO)
{
    commandGateway.send(new CreateFormationCommand(
            formationDTO.getIdFormation(),
            formationDTO.getNom(),
            formationDTO.getDuree()));

    return "created";
}


@PostMapping("/formation/{idf}/etudiant")
    public String addEtudiant(@PathVariable String  idf, @RequestBody etudiantDTO etudiantDTO)
{
    commandGateway.send(new AddEtudiantCommand(
            idf,
            etudiantDTO.getIdEtudiant(),
            etudiantDTO.getNom(),
            etudiantDTO.getDateN()
    ));

    return "Added";
}


    @PutMapping("/formation/{idf}/etudiant/{ide}")
    public String UpdateEtudiant(@PathVariable String idf, @PathVariable Long ide, @RequestBody etudiantDTO etudiantDTO)
    {
        commandGateway.send(new UpdateEtudiantCommand(
                idf,
                ide,
                etudiantDTO.getNom(),
                etudiantDTO.getDateN()));

        return "updated";
    }

    @DeleteMapping("/formation/{id}/etudiant/{ide}")
    public String DeleteEtudiant(@PathVariable String id, @PathVariable Long ide)
    {
        commandGateway.send(new RemoveEtudiantCommand(id,ide));
        return "removed";
    }
}
