package com.hkunitedauction.cloud.apigateway.filter;

import com.hkunitedauction.util.security.JwtProvider;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationPreFilter extends ZuulFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        attachCurrentUser();
        return null;
    }

    private void attachCurrentUser(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if(request.getHeader("Authorization") == null){
            return;
        }

        String token = ctx.getRequest().getHeader("Authorization").replace("Bearer ", "");
        Map<String, String[]> map  = request.getParameterMap();
        if(map == null){
            map = new HashMap<>();
        }

        if(map.containsKey("currentUser") || !jwtProvider.validateJwtToken(token)){
            return;
        }

        String currentUser = jwtProvider.getUserNameFromJwtToken(token);
        map.put("currentUser", new String[]{ currentUser });

        Map<String, List<String>> params = ctx.getRequestQueryParams();
        if(params == null){
            params = new HashMap<>();
        }

        for(String key : map.keySet()){
            if(params.containsKey(key)){
                continue;
            }
            params.put(key, Arrays.asList(map.get(key)));
        }
        ctx.setRequestQueryParams(params);
    }
}
