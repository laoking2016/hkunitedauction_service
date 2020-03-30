package com.hkunitedauction.util;

import org.apache.ibatis.session.RowBounds;
import org.springframework.web.util.UriComponentsBuilder;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryBuilder{

    public static Map<String, List<String>> buildParams(String source){
        if(source == null){
            return new HashMap<String, List<String>>();
        }
        String url = String.format("http://localhost?%s", source);
        return UriComponentsBuilder.fromUriString(url).build().getQueryParams();
    }

    public static Example.Criteria buildCriteria(Example example, Map<String, List<String>> params, String... excludes){

        Example.Criteria critiera = example.createCriteria();
        DateFormat df = new SimpleDateFormat();

        for(String name : params.keySet()){

            if("sortby".equalsIgnoreCase(name)){
                continue;
            }
            if("order".equalsIgnoreCase(name)){
                continue;
            }
            if("page".equalsIgnoreCase(name)){
                continue;
            }
            if("pagesize".equalsIgnoreCase(name)){
                continue;
            }
            if(Arrays.asList(excludes).contains(name)){
                continue;
            }

            String[] values = params.get(name).toArray(new String[0]);

            Field field = null;
            Object[] vals = null;

            try {
                Class<?> clazz = example.getEntityClass();

                field = clazz.getDeclaredField(name);
                String fieldType = field.getType().getName();

                switch (fieldType) {

                    case "java.lang.Long":
                        vals = Arrays.stream(values).map(e -> e != null && e != "" ? Long.parseLong(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    case "java.lang.Integer":
                        vals = Arrays.stream(values).map(e -> e != null && e != "" ? Integer.parseInt(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    case "java.util.Date":
                        vals = Arrays.stream(values).map(e -> {
                            try {
                                if(e != null && e !=""){
                                    return df.parse(e);
                                }else {
                                    return null;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return null;
                            }
                        }).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    default:
                        vals = Arrays.stream(values).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            if(vals.length > 1){
                critiera.andIn(name, Arrays.asList(vals));
            }else{
                critiera.andEqualTo(name, vals[0]);
            }
        }

        if(!Arrays.asList(excludes).contains("deleted")){
            critiera.andEqualTo("deleted", false);
        }

        return critiera;
    }

    public static Example.OrderBy buildSortBy(Example example, Map<String, List<String>> params){

        Example.OrderBy orderBy = null;

        for(String item : params.keySet()){
            if(orderBy == null){
                orderBy = example.orderBy(item);
            }else{
                orderBy = orderBy.orderBy(item);
            }
            String value = params.get(item).get(0);
            if(value == null){
                orderBy = orderBy.asc();
            }else if("asc".equalsIgnoreCase(value)){
                orderBy = orderBy.asc();
            }else if("desc".equalsIgnoreCase(value)){
                orderBy = orderBy.desc();
            }
        }

        return orderBy;
    }

    public static RowBounds buildRowBounds(Integer page, Integer pagesize){

        if(page == null){
            page = 1;
        }

        if(pagesize == null){
            pagesize = 1000;
        }

        return new RowBounds((page - 1) * pagesize, pagesize);
    }

    /*public static Example.Criteria buildCriteria(Example example, Map<String, String[]> params, String... excludes){

        Example.Criteria critiera = example.createCriteria();
        DateFormat df = new SimpleDateFormat();

        for(String name : params.keySet()){

            if("sortby".equalsIgnoreCase(name)){
                continue;
            }
            if("order".equalsIgnoreCase(name)){
                continue;
            }
            if("page".equalsIgnoreCase(name)){
                continue;
            }
            if("pagesize".equalsIgnoreCase(name)){
                continue;
            }
            if(Arrays.asList(excludes).contains(name)){
                continue;
            }

            String[] values = params.get(name);

            Field field = null;
            Object[] vals = null;

            try {
                Class<?> clazz = example.getEntityClass();

                field = clazz.getDeclaredField(name);
                String fieldType = field.getType().getName();

                switch (fieldType) {

                    case "java.lang.Long":
                        vals = Arrays.stream(values).map(e -> e != null && e != "" ? Long.parseLong(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    case "java.lang.Integer":
                        vals = Arrays.stream(values).map(e -> e != null && e != "" ? Integer.parseInt(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    case "java.util.Date":
                        vals = Arrays.stream(values).map(e -> {
                            try {
                                if(e != null && e !=""){
                                    return df.parse(e);
                                }else {
                                    return null;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return null;
                            }
                        }).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                    default:
                        vals = Arrays.stream(values).collect(Collectors.toList()).toArray(new Object[0]);
                        break;
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            if(vals.length > 1){
                critiera.andIn(name, Arrays.asList(vals));
            }else{
                critiera.andEqualTo(name, vals[0]);
            }
        }

        if(!Arrays.asList(excludes).contains("deleted")){
            critiera.andEqualTo("deleted", false);
        }

        return critiera;
    }

    public static Example.OrderBy buildSortBy(Example example, Map<String, String[]> params){

        String[] sortBys = null;
        String[] orders = null;
        Example.OrderBy orderBy = null;

        if(params.containsKey("sortby")) {
            sortBys = params.get("sortby");
            orders = null;
            if(params.containsKey("order")){
                orders = params.get("order");
            }

            if(sortBys.length != orders.length){
                return null;
            }

            orderBy = null;
            for(int i = 0; i < sortBys.length; i++){
                if(orderBy == null){
                    orderBy = example.orderBy(sortBys[i]);
                }else{
                    orderBy = orderBy.orderBy(sortBys[i]);
                }
                if("desc".equalsIgnoreCase(orders[i])){
                    orderBy = orderBy.desc();
                }else{
                    orderBy = orderBy.asc();
                }
            }
        }

        return orderBy;
    }

    public static RowBounds buildRowBounds(Map<String, String[]> params){

        int page = 1;
        int pageSize = 1000;

        if(params.containsKey("page")){
            page = Integer.parseInt(params.get("page")[0]);
        }

        if(params.containsKey("pagesize")){
            pageSize = Integer.parseInt(params.get("pagesize")[0]);
        }

        return new RowBounds((page - 1) * pageSize, pageSize);
    }*/
}
