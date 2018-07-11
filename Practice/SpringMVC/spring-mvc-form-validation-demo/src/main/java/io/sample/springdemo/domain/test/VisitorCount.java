package io.sample.springdemo.domain.test;

import java.io.Serializable;

public class VisitorCount implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private int count;
    
    public VisitorCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
