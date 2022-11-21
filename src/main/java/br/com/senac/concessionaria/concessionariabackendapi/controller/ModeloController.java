package br.com.senac.concessionaria.concessionariabackendapi.controller;

import br.com.senac.concessionaria.concessionariabackendapi.dto.ModeloRequest;
import br.com.senac.concessionaria.concessionariabackendapi.dto.ModeloResponse;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Modelo;
import br.com.senac.concessionaria.concessionariabackendapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/modelo"})
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarModelo(@RequestBody ModeloRequest modelo){

        Modelo modeloModel = new Modelo();

        modeloModel.setNome(modelo.getNome());
        try {
            modeloRepository.save(modeloModel);
            return ResponseEntity.ok().body(null);

        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<ModeloResponse>> retornarModelos(){
        List<Modelo> modeloList = new ArrayList<>();
        modeloList = modeloRepository.findAll();
        List<ModeloResponse> modeloResponseList = new ArrayList<>();
        for (Modelo modelo : modeloList){
            modeloResponseList.add(new ModeloResponse(modelo.getId(), modelo.getNome()));
        }
        return ResponseEntity.ok().body(modeloResponseList);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> removerModelo(){
        modeloRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarModelos(){
        modeloRepository.deleteAll();
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> atualizaModelo(@RequestBody ModeloRequest modeloRequest, @PathVariable Long id){
        Optional<Modelo> modelo;
        modelo = modeloRepository.findById(id)
                .map(record -> {
                    record.setNome(modeloRequest.getNome());
                    return modeloRepository.save(record);
        });
        return ResponseEntity.ok().body(null);
    }
}
