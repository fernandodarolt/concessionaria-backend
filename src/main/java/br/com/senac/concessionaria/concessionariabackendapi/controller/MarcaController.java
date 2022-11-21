package br.com.senac.concessionaria.concessionariabackendapi.controller;

import br.com.senac.concessionaria.concessionariabackendapi.dto.MarcaRequest;
import br.com.senac.concessionaria.concessionariabackendapi.dto.MarcaResponse;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Marca;
import br.com.senac.concessionaria.concessionariabackendapi.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/marca"})
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marca){

        Marca marcaModel = new Marca();

        marcaModel.setNome(marca.getNome());
        marcaModel.setDescricao(marca.getDescricao());

        marcaRepository.save(marcaModel);

        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<MarcaResponse>> retornarMarcas(){

        //Cria lista vazia
        List<Marca> marcaList = new ArrayList<>();

        //Carrega informações do banco de dados e adiciona na lista vazia
        marcaList = marcaRepository.findAll();

        //Cria lista vazia para retorno
        List<MarcaResponse> marcaResponseList = new ArrayList<>();

        //Laço de repetição para percorrer a lista de marcas
        for(Marca marca : marcaList){
            marcaResponseList.add(new MarcaResponse(marca.getId(), marca.getNome(), marca.getDescricao()));
        }
        return ResponseEntity.ok().body(marcaResponseList);
    }


    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> removerMarca(@PathVariable Long id){
        marcaRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarMarcas(){
        marcaRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> atualizarMarca(@RequestBody MarcaRequest marcaRequest, @PathVariable Long id){
        Optional<Marca> marca;
        marca = marcaRepository.findById(id)
                .map(record -> {
                    record.setDescricao(marcaRequest.getDescricao());
                    record.setNome(marcaRequest.getNome());
                    return marcaRepository.save(record);
            });
        return ResponseEntity.ok().body(null);
    }

}
