package br.com.senac.concessionaria.concessionariabackendapi.controller;

import br.com.senac.concessionaria.concessionariabackendapi.dto.MarcaResponse;
import br.com.senac.concessionaria.concessionariabackendapi.dto.ModeloRequest;
import br.com.senac.concessionaria.concessionariabackendapi.dto.ModeloResponse;
import br.com.senac.concessionaria.concessionariabackendapi.dto.PlacaResponse;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Marca;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Modelo;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Placa;
import br.com.senac.concessionaria.concessionariabackendapi.repository.MarcaRepository;
import br.com.senac.concessionaria.concessionariabackendapi.repository.ModeloRepository;
import br.com.senac.concessionaria.concessionariabackendapi.repository.PlacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/modelo"})
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private PlacaRepository placaRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarModelo(@RequestBody ModeloRequest modeloRequest){
        Modelo modeloModel = new Modelo();
        Optional<Marca> marca = marcaRepository.findById(modeloRequest.getIdMarca());
        Optional<Placa> placa = placaRepository.findById(modeloRequest.getIdPlaca());
        modeloModel.setNome(modeloRequest.getNome());
        modeloModel.setMarca(marca.get());
        modeloModel.setPlaca(placa.get());
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
            Marca marca = modelo.getMarca();
            Placa placa = modelo.getPlaca();
            MarcaResponse marcaResponse = new MarcaResponse();
            if (marca != null){
                marcaResponse.setId(marca.getId());
                marcaResponse.setDescricao(marca.getDescricao());
                marcaResponse.setNome(marca.getNome());
            }
            PlacaResponse placaResponse = new PlacaResponse();
            if (placa != null){
                placaResponse.setId(placa.getId());
                placaResponse.setNumero(placa.getNumero());
            }
            modeloResponseList.add(new ModeloResponse(modelo.getId(), modelo.getNome(), marcaResponse, placaResponse));
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
    @GetMapping(path =  {"/{id}"})
    public ResponseEntity<ModeloResponse> carregarModeloId(@PathVariable Long id){
        Optional<Modelo> modelo = modeloRepository.findById(id);
        ModeloResponse modeloResponse = new ModeloResponse();
        modeloResponse.setId(modelo.get().getId());
        modeloResponse.setNome(modelo.get().getNome());
        return ResponseEntity.ok().body(modeloResponse);
    }
}
