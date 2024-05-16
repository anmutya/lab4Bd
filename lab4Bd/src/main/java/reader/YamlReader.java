/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reader;


import reactor.Reactor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;


/**
 *
 * @author annamutovkina
 */
public class YamlReader implements ReaderFile{
    @Override
    public ArrayList<Reactor> readFile(String path) {
        ArrayList<Reactor> reactors = new ArrayList<>();
        
        try (FileInputStream inputStream = new FileInputStream(new File(path))) {
            Yaml yaml = new Yaml();
            Iterable<Object> itr = yaml.load(inputStream);
            for (Object obj : itr) {
                    LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) obj;
                    if (data.containsKey("type") && data.containsKey("burnup") && data.containsKey("kpd")
                        && data.containsKey("enrichment") && data.containsKey("thermal_capacity")
                        && data.containsKey("electrical_capacity") && data.containsKey("life_time")
                        && data.containsKey("first_load")) {

                        Reactor reactor;
                        reactor = new Reactor(
                                String.valueOf(data.get("type")),
                                Double.parseDouble(String.valueOf(data.get("burnup"))),
                                Double.parseDouble(String.valueOf(data.get("kpd"))),
                                Double.parseDouble(String.valueOf(data.get("enrichment"))), 
                                Integer.parseInt(String.valueOf(data.get("thermal_capacity"))), 
                                Double.parseDouble(String.valueOf(data.get("electrical_capacity"))),
                                Integer.parseInt(String.valueOf(data.get("life_time"))),
                                Double.parseDouble(String.valueOf(data.get("first_load"))));
                        reactor.setSource("yaml");
                        reactors.add(reactor);
                    } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e ){
            e.printStackTrace();
        }
        return reactors;
    }

}
