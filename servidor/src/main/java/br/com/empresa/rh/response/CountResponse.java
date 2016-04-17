/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.response;

/**
 *
 * @author charles
 */
public class CountResponse {
    private long count;

    public CountResponse(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
    
}
