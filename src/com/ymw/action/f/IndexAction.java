package com.ymw.action.f;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;

public class IndexAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555221060422584908L;

	//========================================上面是注入方法====下面是应用方法===============================================//
	@Override
	public String execute(){
		
		return Action.SUCCESS;
	}
	
}
