package me.hyunsoo.howtostream.beans;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MaleStudent {
    private List<Kid> list;

    public MaleStudent(){
        list = new ArrayList<Kid>();
        System.out.println("[" + Thread.currentThread().getName() + "] MaleStudent()");
    }

    public void accumulate(Kid kid){
        list.add(kid);
        System.out.println("[" + Thread.currentThread().getName() + "] accumulate()");
    }

    public void combine(MaleStudent other){
        list.addAll(other.getList());
        System.out.println("[" + Thread.currentThread().getName() + "] combine()");
    }
}
