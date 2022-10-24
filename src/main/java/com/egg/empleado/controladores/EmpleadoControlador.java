package com.egg.empleado.controladores;

import com.egg.empleado.exception.ResourceNotFoundException;
import com.egg.empleado.model.dto.EmpleadoRequest;
import com.egg.empleado.model.dto.EmpleadoResponse;
import com.egg.empleado.model.dto.ListEmpleadoResponse;
import com.egg.empleado.servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {

    @Autowired
    public EmpleadoServicio empleadoServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<EmpleadoResponse> nuevo(@RequestBody EmpleadoRequest request){

        System.out.println(request.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoServicio.crear(request));
    }


    @GetMapping
    public ResponseEntity<ListEmpleadoResponse> lista() {

        return ResponseEntity.ok().body(empleadoServicio.listar());

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        empleadoServicio.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(empleadoServicio.getResponseByID(id));


    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmpleadoResponse> update(@PathVariable Long id, @RequestBody EmpleadoRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(empleadoServicio.update(request, id));
    }



}
