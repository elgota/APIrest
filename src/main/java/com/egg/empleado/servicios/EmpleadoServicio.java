package com.egg.empleado.servicios;

import com.egg.empleado.entidades.Empleado;
import com.egg.empleado.exception.ResourceNotFoundException;
import com.egg.empleado.mapper.EmpleadoMapper;
import com.egg.empleado.model.dto.EmpleadoRequest;
import com.egg.empleado.model.dto.EmpleadoResponse;
import com.egg.empleado.model.dto.ListEmpleadoResponse;
import com.egg.empleado.repositorios.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private EmpleadoMapper mapper;

    public EmpleadoResponse crear(EmpleadoRequest request) {

        Empleado empleado = mapper.map(request);

        empleadoRepositorio.save(empleado);

        return mapper.map(empleado);
    }

    public ListEmpleadoResponse listar() {

        List<Empleado> empleados = empleadoRepositorio.findAll();

        ListEmpleadoResponse empleadoResponse = new ListEmpleadoResponse();
        empleadoResponse.setEmpleados(mapper.map(empleados));

        return empleadoResponse;

    }

    public void delete(Long id) throws ResourceNotFoundException {
        findById(id);
        empleadoRepositorio.deleteById(id);
    }

    public EmpleadoResponse update(EmpleadoRequest request, Long id) throws ResourceNotFoundException {

        Empleado empleado = findById(id);
        empleado.setNombre(request.getNombre());
        empleado.setApellido(request.getApellido());
        empleado.setSector(request.getSector());

        empleadoRepositorio.save(empleado);
        return mapper.map(empleado);


    }

    public Empleado findById(Long ID) throws ResourceNotFoundException{

        Optional<Empleado> empleado = empleadoRepositorio.findById(ID);
        if (empleado.isPresent()) {

            return empleado.get();

        } else {

            throw new ResourceNotFoundException("Este empleado no existe");

        }
    }

    public EmpleadoResponse getResponseByID(Long ID) throws ResourceNotFoundException {
        return mapper.map(findById(ID));
    }

}
