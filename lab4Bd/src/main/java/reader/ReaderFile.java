/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reader;
import reactor.Reactor;
import java.util.ArrayList;


/**
 *
 * @author annamutovkina
 */
public interface ReaderFile {

    public ArrayList<Reactor> readFile(String path);
}
