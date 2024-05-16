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
public class XMLhandler extends Handler{

    public XMLhandler(ReaderFile reader) {
        super(reader);
    }
    @Override
    public void handleRequest(String path) {
        if(path.endsWith(".xml")){
           storage.setReactors("xml", readerFile.readFile(path));
        }
        else{
            next.handleRequest(path);
        }
    }
}
