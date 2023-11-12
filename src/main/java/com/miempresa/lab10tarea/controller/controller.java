package com.miempresa.lab10tarea.controller;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero <= 3) {
            return true;
        }
        if (numero % 2 == 0 || numero % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= numero; i = i + 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    public static List<String> encontrarNumerosPrimosEnRango(int inicio, int fin) {
        List<String> primos = new ArrayList<>();
        for (int numero = inicio; numero <= fin; numero++) {
            if (esPrimo(numero)) {
                primos.add(String.valueOf(numero));
            }
        }
        return primos;
    }
    public static List<TablaMultiplicar> listarTabla(int tabla){
        List<TablaMultiplicar> tablaLlena = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int resultado = tabla * i;
            String operacion = tabla + "x" + i;
            TablaMultiplicar fila = new TablaMultiplicar(operacion, resultado);
            tablaLlena.add(fila);
        }
        return tablaLlena;
    }
    static class TablaMultiplicar {
        public String operacion;
        public double resultado;

        public TablaMultiplicar(String operacion, double resultado) {
            this.operacion = operacion;
            this.resultado = resultado;
        }

        @Override
public String toString() {
    return operacion;
}
    }
    @GetMapping("/resultados")
    public String resultados(
            @RequestParam(name = "npInicio", required = false, defaultValue = "1") String npInicio,
            @RequestParam(name = "npFin", required = false, defaultValue = "2") String npFin,
            Model model) {
        List<String> array = null;
        
        Integer numInicio = Integer.parseInt(npInicio);
        Integer numFin = Integer.parseInt(npFin);

        array = encontrarNumerosPrimosEnRango(numInicio, numFin);
        model.addAttribute("arrayTest", array);
        return "resultados";
    }

    @GetMapping("/resultados2")
    public String resultados2(@RequestParam(name = "tabla", required = false, defaultValue = "1") String tabla,Model model){
        List<TablaMultiplicar> array = null;
        Integer ntabla = Integer.parseInt(tabla);
        array = listarTabla(ntabla);
        model.addAttribute("arrayTest", array);
        return "resultados2";
    }
}