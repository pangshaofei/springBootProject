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
package com.common.project.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


public class JSON {

	private static JsonMapper mapper = new JsonMapper();
	
	public static String toJson(Object object) {
		return mapper.toJson(object);
	}

	public static <T> T parseObject(String jsonString, Class<T> clazz) {
		return mapper.fromJson(jsonString, clazz);
	}
	
	public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
		JavaType jt = mapper.createCollectionType(List.class, clazz);
		return mapper.fromJson(jsonString, jt);
	}
	
	public static <T> Set<T> parseSet(String jsonString, Class<T> clazz) {
		JavaType jt = mapper.createCollectionType(Set.class, clazz);
		return mapper.fromJson(jsonString, jt);
	}
	
	public static <K, V> Map<K, V> parseMap(String jsonString, Class<K> keyclz, Class<V> valclz) {
		JavaType jt = mapper.createCollectionType(Map.class, keyclz, valclz);
		return mapper.fromJson(jsonString, jt);
	}
	
	public static <T> T from (String json, JavaType jt) {
		return mapper.fromJson(json, jt);
	}

	public static JavaType type (Class<?> clazz1, Class<?> clazz2) {
		return mapper.getMapper().getTypeFactory().constructParametricType(clazz1, clazz2);
	}
	
	public static JavaType type (Class<?> clazz1, Class<?> clazz2, Class<?> clazz3) {
		return mapper.getMapper().getTypeFactory().constructParametricType(clazz1, clazz2, clazz3);
	}

	public static JavaType type (Class<?> clazz1, JavaType clazz2) {
		return mapper.getMapper().getTypeFactory().constructParametricType(clazz1, clazz2);
	}
	
	public static enum FilterBy{
		OUTEXCEPT,
		EXCEPT
	}
	
	public static ObjectWriter filterWriter(Class<?> target, Class<?> mixinSource, String filterName, FilterBy filterBy, String ... properties){
		ObjectMapper mapper = new ObjectMapper().addMixIn(target, mixinSource);
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = filterBy == null ? null : (filterBy == FilterBy.OUTEXCEPT ? SimpleBeanPropertyFilter.filterOutAllExcept(properties) : SimpleBeanPropertyFilter.serializeAllExcept(properties));
		return mapper.writer(new SimpleFilterProvider().addFilter(filterName, simpleBeanPropertyFilter));
	}
}
