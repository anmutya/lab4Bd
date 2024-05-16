/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handlers;

import reader.ReaderFile;

/**
 *
 * @author annamutovkina
 */
public class JSONhandler extends Handler{

    public JSONhandler(ReaderFile reader) {
        super(reader);
    }
    @Override
    public void handleRequest(String path) {
        if(path.endsWith(".json")){
           storage.setReactors("json", readerFile.readFile(path));
        }
        else{
            next.handleRequest(path);
        }
    } 
}
