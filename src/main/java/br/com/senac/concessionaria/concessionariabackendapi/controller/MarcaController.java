package br.com.senac.concessionaria.concessionariabackendapi.controller;

import br.com.senac.concessionaria.concessionariabackendapi.dto.MarcaRequest;
import br.com.senac.concessionaria.concessionariabackendapi.dto.MarcaResponse;
import br.com.senac.concessionaria.concessionariabackendapi.dto.ModeloResponse;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Marca;
import br.com.senac.concessionaria.concessionariabackendapi.modelo.Modelo;
import br.com.senac.concessionaria.concessionariabackendapi.repository.MarcaRepository;
import br.com.senac.concessionaria.concessionariabackendapi.repository.ModeloRepository;
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

    @Autowired
    private ModeloRepository modeloRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marca){

        Marca marcaModel = new Marca();

        marcaModel.setNome(marca.getNome());
        marcaModel.setDescricao(marca.getDescricao());

        marcaRepository.save(marcaModel);

        return ResponseEntity.ok().body(null);
    }

    //Lista todas marcas
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

    //Lista pelo ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<MarcaResponse> carregarMarcaId(@PathVariable Long id){
        Optional<Marca> marca = marcaRepository.findById(id);
        MarcaResponse marcaResponse = new MarcaResponse();
        marcaResponse.setId(marca.get().getId());
        marcaResponse.setNome(marca.get().getNome());
        marcaResponse.setDescricao(marca.get().getDescricao());
        return ResponseEntity.ok().body(marcaResponse);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/modelos"})
    public ResponseEntity<List<MarcaResponse>> retornarMarcasModelo(){
        List<Marca> marcaList = new ArrayList<>();
        marcaList = marcaRepository.findAll();
        List<MarcaResponse> marcaResponseList = new ArrayList<>();
        for(Marca marca : marcaList){
            List<Modelo> modeloList = modeloRepository.getModelos(marca.getId());
            List<ModeloResponse> modeloResponses = new ArrayList<>();
            for (Modelo modelo : modeloList){
                modeloResponses.add(new ModeloResponse(modelo.getId(), modelo.getNome()));
            }
            marcaResponseList.add(new MarcaResponse(marca.getId(), marca.getNome(), marca.getDescricao(), modeloResponses));
        }
        return ResponseEntity.ok().body(marcaResponseList);
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/modelos/{id}"})
    public ResponseEntity<List<MarcaResponse>> retornarMarcasModeloId(@PathVariable Long id){
        Optional<Marca> marca = marcaRepository.findById(id);
        MarcaResponse marcaResponse = new MarcaResponse();
        marcaResponse.setId(marca.get().getId());
        marcaResponse.setNome(marca.get().getNome());
        marcaResponse.setDescricao(marca.get().getDescricao());
        List<Modelo> modeloList = modeloRepository.getModelos(marcaResponse.getId());
        List<ModeloResponse> modeloResponses = new ArrayList<>();
        for (Modelo modelo : modeloList){
            modeloResponses.add(new ModeloResponse(modelo.getId(), modelo.getNome()));
        }
        List<MarcaResponse> marcaResponseList = new ArrayList<>();
        marcaResponseList.add(new MarcaResponse(marcaResponse.getId(), marcaResponse.getNome(), marcaResponse.getDescricao(), modeloResponses));
        return ResponseEntity.ok().body(marcaResponseList);
    }


}
