package org.pepstock.charba.client.items;

import java.lang.reflect.Method;

import org.pepstock.charba.client.colors.IsColor;

public class Test {

	public static void main(String[] args) {
		Method[] ms = DatasetViewItem.class.getDeclaredMethods();
		for (Method m : ms) {
			int start = m.getName().startsWith("is") ? 2 : 3;
			String nativeName;
			if (m.getName().startsWith("is")) {
				nativeName = "isNative"+m.getName().substring(start);
			} else {
				nativeName = "getNative"+m.getName().substring(start);
			}
			String first = m.getName().substring(start, start+1);
			String p = first.toLowerCase()+m.getName().substring(start+1);
			System.out.println("@JsProperty(name=\""+p+"\")");
			if (m.getReturnType().toString().equals("boolean")) {
				System.out.println("native boolean "+nativeName+"();");
			} else if (m.getReturnType().toString().equals("double")) {
				System.out.println("native double "+nativeName+"();");
			} else if (m.getReturnType().toString().equals("int")) {
				System.out.println("native int "+nativeName+"();");
			} else if (m.getReturnType().equals(String.class)) {
				System.out.println("native String "+nativeName+"();");
			} else if (m.getReturnType().equals(IsColor.class)) {
				System.out.println("native String "+nativeName+"();");
			} else  {
				System.out.println("native String "+nativeName+"();");
			}
		}
	}

}
