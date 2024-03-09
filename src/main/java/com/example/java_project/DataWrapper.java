package com.example.java_project;

import java.io.Serializable;

public class DataWrapper implements Serializable {
    public String command;
    public Object obj;
    DataWrapper(String command, Object obj)
    {
        this.obj=obj;
        this.command=command;
    }
    DataWrapper()
    {

    }
}
