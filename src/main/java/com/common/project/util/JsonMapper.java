
package com.common.project.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;


public class JsonMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);

	private ObjectMapper mapper;

	public JsonMapper() {
		this(null);
	}

	public JsonMapper(Include include) {
		mapper = new ObjectMapper();
		//设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性		
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}

	public static JsonMapper nonNullMapper() {
		return new JsonMapper(Include.NON_NULL);
	}


	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}


	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			LOGGER.warn("write to json string error:" + object, e);
			return null;
		}
	}


	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (jsonString == null || jsonString.length() <= 0) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			LOGGER.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
		if (jsonString == null || jsonString.length() <= 0) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, typeReference);
		} catch (IOException e) {
			LOGGER.warn("parse Collection<Bean> json string error:" + jsonString, e);
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (jsonString == null || jsonString.length() <= 0) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			LOGGER.warn("parse javaType json string error:" + jsonString, e);
			return null;
		}
	}


	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}


	@SuppressWarnings("unchecked")
	public <T> T update(String jsonString, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			LOGGER.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}


	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}


	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}


	public ObjectMapper getMapper() {
		return mapper;
	}
}
