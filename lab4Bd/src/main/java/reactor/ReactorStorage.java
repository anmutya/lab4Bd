/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author annamutovkina
 */
public enum ReactorStorage {
    INSTANCE;
    private HashMap<String, ArrayList<Reactor>> reactorsMap = new HashMap<>();

    public ArrayList<Reactor> getReactors(String source) {
        return reactorsMap.get(source);
    }

    public void setReactors(String source, ArrayList<Reactor> reactors) {
        reactorsMap.put(source, reactors);
    }

    public HashMap<String, ArrayList<Reactor>> getReactors() {
        return reactorsMap;
    }

    public void clearReactors() {
        this.reactorsMap.clear();
    }


}
