package com.leejua.myproject.proxy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("inventory") @Lazy
public class Inventory<T> {
    private ArrayList<T> inventory;
    public  Inventory(){ inventory = new ArrayList<>();
    }
    public void add(T t){inventory.add(t);}
    public void clear(){inventory.clear();}
    public T get(int i){ return inventory.get(i);}
    public ArrayList get(){return inventory;}
    public int size(){return inventory.size();}
    public void newInventory(){inventory = new ArrayList<>();}

}