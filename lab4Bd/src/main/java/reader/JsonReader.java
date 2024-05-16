/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reader;

import reactor.Reactor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;


/**
 *
 * @author annamutovkina
 */
public class JsonReader implements ReaderFile{
    public ArrayList<Reactor> readFile() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reactor> reactors = null;
        try {
            JsonNode rootNode = mapper.readTree(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("ReactorType.json")).getPath()));
            JsonNode reactorListNode = rootNode.get("reactorList");
            reactors = mapper.readValue(reactorListNode.toString(), new TypeReference<ArrayList<Reactor>>() {});
        } catch (IOException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Reactor r: reactors){
            r.setSource("json");
        }
        return reactors;
    }
    @Override
    public ArrayList<Reactor> readFile(String path) {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Reactor> reactors = null;
    try {
        JsonNode rootNode = mapper.readTree(new File(path));
        JsonNode reactorListNode = rootNode.get("reactorList");
        reactors = mapper.readValue(reactorListNode.toString(), new TypeReference<ArrayList<Reactor>>() {});
    } catch (IOException ex) {
        Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    for(Reactor r: reactors){
        r.setSource("json");
    }
    return reactors;
    }

    
}
