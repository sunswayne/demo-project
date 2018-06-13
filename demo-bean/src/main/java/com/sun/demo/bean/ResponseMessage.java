package com.sun.demo.bean;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.demo.common.DateTimeUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 响应消息。controller中处理后，返回此对象，响应请求结果给客户端。
 */

public class ResponseMessage implements Serializable {
    public static final int OK=1;
    private static final long serialVersionUID = 8992436576262574064L;
    /**
     * 响应码
     */
    private int code;
    /**
     * 反馈信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient String message;
    /**
     * 反馈数据
     */
    private Object data;
    /**
     * 过滤字段：指定需要序列化的字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient Map<Class<?>, Set<String>> includes;
    /**
     * 过滤字段：指定不需要序列化的字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient Map<Class<?>, Set<String>> excludes;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private transient String callback;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (data != null)
            map.put("data", this.getData());
        if (message != null)
            map.put("message", this.getMessage());
        map.put("code", this.getCode());
        return map;
    }

    protected ResponseMessage(String message) {
        this(0,message,"");
    }
    protected ResponseMessage(int code,String message) {
        this(code,message,"");
    }
    protected ResponseMessage(int code,String message, Object data) {
        this.code = code;
        this.message=message;
        this.data=data;
    }

    public ResponseMessage include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }

    public ResponseMessage include(Class<?> type, Collection<String> fields) {
        if (includes == null)
            includes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFormMap(includes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage exclude(Class type, Collection<String> fields) {
        if (excludes == null)
            excludes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFormMap(excludes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage exclude(Collection<String> fields) {
        if (excludes == null)
            excludes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        Class type;
        if (data != null) type = data.getClass();
        else return this;
        exclude(type, fields);
        return this;
    }

    public ResponseMessage include(Collection<String> fields) {
        if (includes == null)
            includes = new HashMap<>();
        if (fields == null || fields.isEmpty()) return this;
        Class type;
        if (data != null) type = data.getClass();
        else return this;
        include(type, fields);
        return this;
    }

    public ResponseMessage exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    public ResponseMessage exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    public ResponseMessage include(String... fields) {
        return include(Arrays.asList(fields));
    }

    protected Set<String> getStringListFormMap(Map<Class<?>, Set<String>> map, Class type) {
        Set<String> list = map.get(type);
        if (list == null) {
            list = new HashSet<>();
            map.put(type, list);
        }
        return list;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
//        String msg = "";
//        try{
//            msg = new String(message.getBytes(), "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return msg;
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
    }

    public static ResponseMessage fromJson(String json) {
        return JSON.parseObject(json, ResponseMessage.class);
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return excludes;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return includes;
    }

    public ResponseMessage callback(String callback) {
        this.callback = callback;
        return this;
    }

    public String getCallback() {
        return callback;
    }

    public static ResponseMessage ok() {
        return new ResponseMessage(1,"");
    }

    public static ResponseMessage ok(Object data) {
        if(data==null){
            return error("查询对象为空");
        }else{
            if(data instanceof List<?>){
                List _obj = (List)data;
                if(_obj.size()<=0){
                    return error("未查询到可用数据");
                }
            }
            return new ResponseMessage(1,"", data);
        }
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(message);
    }

    public static ResponseMessage error(int code,String message) {
        return new ResponseMessage(code,message);
    }
    public static ResponseMessage error(int code,String message,Object object) {
        return new ResponseMessage(code,message,object);
    }


}