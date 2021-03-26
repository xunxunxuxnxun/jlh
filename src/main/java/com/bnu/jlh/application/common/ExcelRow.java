package com.bnu.jlh.application.common;

import java.util.ArrayList;

public class ExcelRow extends ArrayList<String> {
    public ExcelRow() {
        super();
    }

    public void addAsNumber(String num) {
        add("number::" + num);
    }

    public boolean isEmptyRow(){
        if(size() == 0){
            return true;
        }

        for(int i=0;i<size();i++){
            if(get(i)!=null && !get(i).isEmpty()){
                return false;
            }
        }

        return true;
    }

    public String getValue(int index){
        if(size() <= index){
            return null;
        }

        return get(index);
    }
}
