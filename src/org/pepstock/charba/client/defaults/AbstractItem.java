package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

public abstract class AbstractItem extends JavaScriptObjectContainer {

	private final AbstractItem parent; 
	
	private final Key childKey;

	protected AbstractItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		this.parent = null;
		this.childKey = null;
	}

	protected AbstractItem(AbstractItem parent, Key childKey) {
		super(parent.load(childKey));
		this.parent = parent;
		this.childKey = childKey;
	}

	protected void checkAndAddToParent(){
		if (parent != null && !parent.has(childKey)){
			parent.setValue(childKey, this);
			parent.checkAndAddToParent();
		}
	}
	
	private GenericJavaScriptObject load(Key key){
		if (has(key)){
			return (GenericJavaScriptObject) getValue(key);
		} else {
			return null;
		}
	}
	
	public boolean hasProperty(Key key){
		return has(key);
	}

}
