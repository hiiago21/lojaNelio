package com.loja.backendlojanelio.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeStringIdsForInt(String ids){
        try{
            String[] vetorIds = ids.split(",");
            return Arrays.stream(vetorIds).map(Integer::parseInt).collect(Collectors.toList());
        }catch (NumberFormatException e){
            return Collections.emptyList();
        }
    }

    public static String decodeParam(String param){
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
