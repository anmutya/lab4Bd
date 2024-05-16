/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package an_mutya.lab4Bd;

import reactor.ReactorStorage;
import reactor.Reactor;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author annamutovkina
 */
public class TreeBuilder {
    private DefaultMutableTreeNode rootTree;

   public DefaultMutableTreeNode buildTreeData() {
        this.rootTree = new DefaultMutableTreeNode("Reactors");
        ArrayList<String> keys = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Reactor>> entry : ReactorStorage.INSTANCE.getReactors().entrySet()) {
            keys.add(entry.getKey());
        }
        initFormatNodes(keys); 

        return rootTree;
    }
   
    private void initFormatNodes(ArrayList<String> keys ) {
        DefaultMutableTreeNode varNode;
        for (String key : keys) {
            varNode = new DefaultMutableTreeNode(key);
            rootTree.add(varNode);
            initReactorNodes(varNode, ReactorStorage.INSTANCE.getReactors().get(key));
        }
    }
    private void initReactorNodes(DefaultMutableTreeNode parent, ArrayList<Reactor> reactors) {
        DefaultMutableTreeNode varNode;
        for (Reactor r : reactors) {

            varNode = new DefaultMutableTreeNode(r.getType());
            parent.add(varNode);
            initPropertiesNodes(varNode, r);
            
        }
    }
    private void initPropertiesNodes(DefaultMutableTreeNode parent, Reactor reactor) {
        DefaultMutableTreeNode varNode;
        varNode = new DefaultMutableTreeNode(reactor.toString());
        parent.add(varNode);  
        }
    }

