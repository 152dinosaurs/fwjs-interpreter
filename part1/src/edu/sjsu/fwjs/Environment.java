package edu.sjsu.fwjs;

// I'm just adding a comment to test GitHub Desktop.

import java.util.Map;
import java.util.HashMap;

public class Environment {
    private Map<String,Value> env = new HashMap<String,Value>();
    private Environment outerEnv;

    /**
     * Constructor for global environment
     */
    public Environment() {}

    /**
     * Constructor for local environment of a function
     */
    public Environment(Environment outerEnv) {
        this.outerEnv = outerEnv;
    }

    /**
     * Handles the logic of resolving a variable.
     * If the variable name is in the current scope, it is returned.
     * Otherwise, search for the variable in the outer scope.
     * If we are at the outermost scope (AKA the global scope)
     * null is returned (similar to how JS returns undefined.
     */
    public Value resolveVar(String varName) {
		if (env.containsKey(varName))
			return env.get(varName);
		else {
			if (outerEnv == null) {
				return new NullVal();
			} else {
				return outerEnv.resolveVar(varName);
			}
		}
    }

    /**
     * Used for updating existing variables.
     * If a variable has not been defined previously in the current scope,
     * or any of the function's outer scopes, the var is stored in the global scope.
     */
	public void updateVar(String key, Value v) {
		Value oldVal = this.resolveVar(key);

		// if the oldVal is null, then variable has not been defined in any scope
		if (new NullVal().equals(oldVal)) {
			Environment e = this;
			// loop until e becomes the global scope
			while (e.outerEnv != null) {
				e = e.outerEnv;
			}
			e.createVar(key, v);
		} else
			oldVal = v;
	}

    /**
     * Creates a new variable in the local scope.
     * If the variable has been defined in the current scope previously,
     * a RuntimeException is thrown.
     */
	public void createVar(String key, Value v) {
		if (env.containsKey(key))
			throw new RuntimeException("Variable already defined in current scope");
		else
			env.put(key, v);
	}
}
