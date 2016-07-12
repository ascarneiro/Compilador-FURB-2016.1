/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gals;

import java.util.ArrayList;

public class Pilha {

    private ArrayList<String> pilha;

    public Pilha() {
        this.pilha = new ArrayList<String>();
    }

    public void empilha(String valor) {
        pilha.add(valor);
    }

    public String desempilha() {
        return pilha.remove(pilha.size() - 1);
    }
    
    public String topoPilha() {
        return pilha.get(pilha.size() - 1);
    }

}
