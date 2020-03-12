package com.hkunitedauction.util;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryBuilder{

        public static Example buildExample(Map<String, String[]> params, Class<?> clazz, String... excludes){

            Example example = new Example(clazz);

            buildCriteria(clazz, example, params, Arrays.asList(excludes));

            if(params.containsKey("sortby")) {
                String[] orders = null;
                if(params.containsKey("order")){
                    orders = params.get("order");
                }
                buildSortBy(example, params.get("sortby"), orders);
            }

            return example;
        }

        private static void buildCriteria(Class<?> clazz, Example example, Map<String, String[]> params, List<String> excludes){

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
                if(excludes.contains(name)){
                    continue;
                }

                String[] values = params.get(name);

                Field field = null;
                Object[] vals = null;

                try {

                    field = clazz.getDeclaredField(name);
                    String fieldType = field.getType().getName();

                    switch (fieldType) {

                        case "java.lang.Long":
                            vals = Arrays.stream(values).map(e -> e != null && e != "" ? Long.getLong(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
                            break;
                        case "java.lang.Integer":
                            vals = Arrays.stream(values).map(e -> e != null && e != "" ? Integer.getInteger(e) : null).collect(Collectors.toList()).toArray(new Object[0]);
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
            critiera.andEqualTo("deleted", false);
        }

        private static void buildSortBy(Example example, String[] sortBys, String[] orders){

            if(sortBys.length != orders.length){
                return;
            }

            Example.OrderBy orderBy = null;
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
        }
}
