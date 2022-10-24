package com.egg.empleado.mapper;

import com.egg.empleado.entidades.Empleado;
import com.egg.empleado.model.dto.EmpleadoRequest;
import com.egg.empleado.model.dto.EmpleadoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpleadoMapper {

    public Empleado map(EmpleadoRequest request) {

        Empleado empleado = new Empleado();

        empleado.setNombre(request.getNombre());
        empleado.setApellido(request.getApellido());
        empleado.setSector(request.getSector());

        return empleado;
    }


    /* mapper que transforma objeto en DTO*/

    public List<EmpleadoResponse> map (List<Empleado> empleados){
        List<EmpleadoResponse> listResponse = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listResponse.add(map(empleado));
        }

        return listResponse;
    }

    public EmpleadoResponse map(Empleado empleado){

        EmpleadoResponse response = new EmpleadoResponse();

        response.setNombreCompleto(empleado.getNombre()+" "+empleado.getApellido());
        response.setSector(empleado.getSector());

        return response;
    }
}
