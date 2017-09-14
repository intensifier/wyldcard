/*
 * SymbolTable
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.runtime.context;

import com.defano.hypertalk.ast.common.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements a list of symbols (variables) and methods for getting and setting
 * their value.
 */
public class SymbolTable {

    private final Map<String, Value> table;
    
    public SymbolTable () {
        table = new HashMap<>();
    }
    
    public Value get (String id) {
        Value v = table.get(id.toLowerCase());
        if (v == null)
            return new Value();
        return v;
    }
    
    public void put (String id, Value v) {
        table.put(id.toLowerCase(), v);
    }
    
    public boolean exists (String id) {
        return table.containsKey(id.toLowerCase());
    }
}