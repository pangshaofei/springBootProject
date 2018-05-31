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

import com.common.project.util.JsonMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class Result<T> {
	
	public int code;
	public T data;
	public String err;
	public String msg;
	
	public Result<T> resSuccess(){
		this.code = 0;
		return this;
	}
	
	public Result<T> resFail(){
		this.code = -1;
		return this;
	}

	@JsonIgnore
	public boolean isSuccess(){
		return code == 0;
	}
	
	@JsonIgnore
	public boolean isFail(){
		return !isSuccess();
	}
	
	public Result<T> data(T data){
		this.data = data;
		return this;
	}
	
	public Result<T> msg(String msg){
		this.msg = msg;
		return this;
	}
	
	public Result<T> err(String err){
		this.err = err;
		return this;
	}
	
	public String json(){
		return JsonMapper.nonNullMapper().toJson(this);
	}
	
	public static <T> Result<T> success(){
		return create(0, null, null, null);
	}
	
	public static <T> Result<T> success(T data){
		return create(0, data, null, null);
	}
	
	public static <T> Result<T> success(String msg, T data){
		return create(0, data, null, msg);
	}
	
	public static <T> Result<T> fail(){
		return create(-1, null, null, null);
	}
	
	public static <T> Result<T> fail(int code){
		return create(code, null, null, null);
	}
	
	public static <T> Result<T> fail(String err){
		return create(-1, null, err, null);
	}
	
	public static <T> Result<T> fail(int code, String err){
		return create(code, null, err, null);
	}
	
	public static <T> Result<T> fail(String err, String msg){
		return create(-1, null, err, msg);
	}
	
	public static <T> Result<T> failData(String err, T data){
		return create(-1, data, err, null);
	}
	
	public static <T> Result<T> create(int code, T data, String err, String msg){
		Result<T> res = new Result<T>();
		res.code = code;
		res.data = data;
		res.err = err;
		res.msg = msg;
		return res;
	}
	
	public static <T> Result<T> fromJson(String json, Class<?> dataClazz){
		JsonMapper jsonMapper = JsonMapper.nonNullMapper();
		return jsonMapper.fromJson(json, jsonMapper.getMapper().getTypeFactory().constructParametricType(Result.class, dataClazz));
	}
}
