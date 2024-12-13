package com.crud.batata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.batata.model.BatataModel;
import com.crud.batata.repository.BatataRepository;

@RestController
@RequestMapping("/batatas")
public class BatataController {

    @Autowired
    private BatataRepository batataRepository;

    
    @PostMapping
    public ResponseEntity<BatataModel> criarBatata(@RequestBody BatataModel batata) {
        BatataModel novaBatata = batataRepository.save(batata);
        return new ResponseEntity<>(novaBatata, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<BatataModel>> listarBatatas() {
        List<BatataModel> batatas = batataRepository.findAll();
        return new ResponseEntity<>(batatas, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<BatataModel> buscarBatataPorId(@PathVariable Long id) {
        Optional<BatataModel> batata = batataRepository.findById(id);
        return batata.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatataModel> atualizarBatata(@PathVariable Long id, @RequestBody BatataModel batataAtualizada) {
        Optional<BatataModel> batataExistente = batataRepository.findById(id);

        if (batataExistente.isPresent()) {
            BatataModel batata = batataExistente.get();
            batata.setTipo(batataAtualizada.getTipo());
            batata.setOrigem(batataAtualizada.getOrigem());
            batata.setPreco(batataAtualizada.getPreco());
            BatataModel batataSalva = batataRepository.save(batata);
            return new ResponseEntity<>(batataSalva, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBatata(@PathVariable Long id) {
        if (batataRepository.existsById(id)) {
            batataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
