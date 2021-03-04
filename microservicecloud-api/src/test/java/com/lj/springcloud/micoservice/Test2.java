package com.lj.springcloud.micoservice;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Test2 {

    @Test
    public void test(){
        String a = "String";
        String b = "String";
        HashSet s = new HashSet();
        s.add(a);
        s.add(b);
        System.out.println(s);

        StringBuilder sb = new StringBuilder("sb");
        StringBuilder sb2 = new StringBuilder("s");
        s.add(sb);
        s.add(sb2);
        sb2.append("b");
        System.out.println(s);
    }

    @Test
    public void test2(){
        StringBuilder sb = new StringBuilder("sb");
        StringBuilder sb2 = new StringBuilder("s");
        Map<Object,Object> map = new HashMap<>();
        map.put(sb,1);
        map.put(sb2,1);
        System.out.println(map);
    }

    
    @Test
    public void testResource(){
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
        LinkedMultiValueMap result = new LinkedMultiValueMap<>();
        Map<ClassLoader, MultiValueMap<String, String>> cache = new ConcurrentReferenceHashMap<>();
        
        try {
            Enumeration<URL> urls = (classLoader != null ?
                    classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
                    ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
            
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    String factoryTypeName = ((String) entry.getKey()).trim();
                    for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                        result.add(factoryTypeName, factoryImplementationName.trim());
                    }
                }
            }
            cache.put(classLoader, result);
            System.out.println(result.size());
            System.out.println(result);
            System.out.println(cache.size());
            System.out.println(cache);
            System.out.println(result.get("org.springframework.boot.autoconfigure.EnableAutoConfiguration"));
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load factories from location [" +
                    FACTORIES_RESOURCE_LOCATION + "]", ex);
        }
    }
    
}
