/**
 *    Copyright 2014-2017 Sodo-Tech Inc.
 *    Website: http://www.sodo-tech.com
 *
 *    Licensed under the GNU Lesser General Public License, Version 3.0 (the "license");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */
package com.common.project.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.common.project.util.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ParamMap extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamMap.class);
	
	public ParamMap press(String k, Object v, Object ... params ){
		put(k, v);
		return pressInner(params);
	}
	
	private ParamMap pressInner(Object ... params){
		if(params == null || params.length < 2 || params.length % 2 != 0)
			return this;
		for(int i=0; i<params.length; i += 2){
			Object key = params[i];
			if(!(key instanceof String)){
				LOGGER.warn("ParamMap: press: the key is not a String. key: " + 
							key + ", params: " + JSON.toJson(params));
			}
			put((String)key, params[i + 1]);
		}
		return this;
	}
	
	@Override
	public ParamMap put(String name, Object val){
		if((val instanceof Collection && 
					((Collection<?>) val ).isEmpty())){
			val = null;
		} else if("".equals(val))
			val = null;
		super.put(name, val);
		return this;
	}

	public ParamMap putSkipHandleEmpty(String name, Object val){
		super.put(name, val);
		return this;
	}
	
	public ParamMap clean(){
		super.clear();
		return this;
	}
	
	public Object get(Object k){
		return super.get(k);
	}
	
	public String json(){
		return JSON.toJson(this);
	}
	
	public static ParamMap createWithMap(Map<String, Object> initMap){
		ParamMap pm = new ParamMap();
		pm.putAll(initMap);
		return pm;
	}
	
	public static ParamMap create(String k, Object v){
		return create(k, v, (Object[])null);
	}
	
	public static ParamMap create(String k, Object v, Object ... params){
		ParamMap pm = new ParamMap();
		pm.press(k, v, params);
		return pm;
	}
	
	public static ParamMap create(){
		return new ParamMap();
	}

	public ParamMap likeword(String key, String keyword){
		if(StringUtils.isBlank(key) || StringUtils.isBlank(keyword)){
			return put(key, null);
		}
		return put(key, "%" + keyword + "%");
	}
	
	
	//-----------------------------移除查询sql中的符号
	private static final boolean[] FILTER_CHAR_INX_ARR;
	private static final String FILTER_CHAR_STR = "%#_!+/~&$*&^＋";
	
	private static int filterCharMaxInx;
	
	static{
		for(char ch : FILTER_CHAR_STR.toCharArray()){
			if(ch > filterCharMaxInx)
				filterCharMaxInx = ch;
		}

		FILTER_CHAR_INX_ARR = new boolean[filterCharMaxInx + 1];
		for(int i=0; i <= filterCharMaxInx; i++)
			FILTER_CHAR_INX_ARR[i] = true;
		for(char c : FILTER_CHAR_STR.toCharArray())
			FILTER_CHAR_INX_ARR[c] = false;
	}
	
	public static String trimKeyword(String keyword){
		if(keyword==null)
			return null;

		StringBuilder sb = new StringBuilder();
		for(char c : keyword.toCharArray()){
			if(c < 0 || c > filterCharMaxInx || FILTER_CHAR_INX_ARR[c])
				sb.append(c);
		}
		return sb.toString();
	}
}
