// cinnamon - the Open Enterprise CMS project
// Copyright (C) 2007-2009 Horner GmbH (http://www.horner-project.eu)
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
// (or visit: http://www.gnu.org/licenses/lgpl.html)

package server.exceptions;

import org.dom4j.Element;
import server.interfaces.ILocalizer;

public class CinnamonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	String[] params = {};
	
	public CinnamonException() {
		super();
	}
	
	public CinnamonException(String message, String... params){
		super(message);
		if(params != null && params.length > 0){
			this.params = params;
		}
	}

	public CinnamonException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

    public CinnamonException(String message, Throwable exception, String... params) {
		super(message, exception);
        if(params != null && params.length > 0){
			this.params = params;
		}
	}

	public CinnamonException(String arg0) {
		super(arg0);
	}

	public CinnamonException(Throwable arg0) {
		super(arg0);
	}
	
	public String[] getParams(){
		return params;
	}
	
	public void addToElement(Element root, ILocalizer localizer){
        String localizedMessage = getMessage();
        if(localizer != null && localizer.getInitialized()){
            localizedMessage = localizer.localize(getMessage());
            if(localizedMessage == null){
                localizedMessage = "null ("+getStackTrace()[0]+")";
            }
        }
		root.addElement("message").addText(localizedMessage);
		root.addElement("code").addText(getMessage() == null ? "null" : getMessage());
		Element paramList = root.addElement("parameters");
		if(params.length > 0){
			for(String p : params){
                if(p == null){
                    p = "null";
                }
				paramList.addElement("param").addText(p);
			}
		}
	}
}
