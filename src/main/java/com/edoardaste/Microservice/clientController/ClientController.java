package com.edoardaste.Microservice.clientController;

import com.edoardaste.Microservice.dtos.ClientRecordsDTO;
import com.edoardaste.Microservice.models.ClientModel;
import com.edoardaste.Microservice.repositories.ClientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value="client controller")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    public static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @ApiOperation(value = "View a list of available CLIENTS",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/clients/")
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    @ApiOperation(value = "Record a client",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recorded a client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping("/client/record")
    public ResponseEntity<ClientModel> postClient(@Valid ClientRecordsDTO clientRecords){
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecords, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @ApiOperation(value = "View a client by id",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getOneById(@PathVariable(value="id") UUID id){
        Optional<ClientModel> client =  clientRepository.findById(id);
        return client.<ResponseEntity<Object>>map(clientModel -> ResponseEntity.status(HttpStatus.OK).body(clientModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!"));

    }

    @ApiOperation(value="Delete client by id", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })
    @DeleteMapping("/client/delete")
    public ResponseEntity<Object> deleteOneById(@PathVariable(value="id") UUID id){
        Optional<ClientModel> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        clientRepository.delete(client.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }


}
