package symboltable;

import java.util.*;
import ast.definitions.Definition;

public class SymbolTable {
	
	private int scope = 0;
	private List<Map<String, Definition>> table;

	public SymbolTable()  {
		this.table = new ArrayList<Map<String, Definition>>();
		this.table.add(new HashMap<String, Definition>());
	}

	public void set() {
		this.scope++; // new scope is opened
		this.table.add(new HashMap<>()); // one Map is needed for each scope
	}
	
	public void reset() {
		this.table.remove(this.scope); // deleting the Map
		this.scope--; // scope is closed
	}
	
	public boolean insert(Definition definition) {
		if(!this.table.get(this.scope).containsKey(definition.getName())){ // NOT already in the table
			definition.setScope(this.scope); // set the scope to the definition to be inserted
			this.table.get(scope).put(definition.getName(), definition);
			return true;
		}
		return false; // otherwise
	}
	
	public Definition find(String id) {
		int currentScope = this.scope;
		for(int i = currentScope; i >= 0; i--){
			if(this.table.get(i).containsKey(id)) {
				return this.table.get(i).get(id); // the value for the given key (the definition)
			}
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		int currentScope = this.scope;
		if(this.table.get(currentScope).containsKey(id)) {
			return table.get(currentScope).get(id);
		}
		return null;
	}
}
