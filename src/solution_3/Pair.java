/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution_3;

/**
 *
 * @author R2-D2
 */
public class Pair {
    private String x;
    private Integer y;

    public Pair(String x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setName(String x) {
        this.x = x;
    }

    public void setCount(Integer y) {
        this.y = y;
    }
    //get 
    public String getName() {
        return x;
    }

    public Integer getCount() {
        return y;
    }
}
