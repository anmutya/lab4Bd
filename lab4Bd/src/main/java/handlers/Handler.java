/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handlers;

import reactor.ReactorStorage;
import reader.ReaderFile;

/**
 *
 * @author annamutovkina
 */
public abstract class Handler {
    protected ReactorStorage storage = ReactorStorage.INSTANCE;
    protected Handler next;
    protected ReaderFile readerFile;

    public Handler(ReaderFile reader) {
        this.readerFile = reader;
    }
    

    public void setNext(Handler next) {
        this.next = next;
    }
    public abstract void handleRequest(String path);
}
