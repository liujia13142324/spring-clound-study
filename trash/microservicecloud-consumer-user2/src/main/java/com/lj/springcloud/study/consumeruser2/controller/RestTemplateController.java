package com.lj.springcloud.study.consumeruser2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lj.springcloud.micoservice.entity.Page;
import com.lj.springcloud.micoservice.entity.Ticket;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

//@RestController
public class RestTemplateController {

    private String serviceUrl = "http://PROVIDER-TICKET";
 //   @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.GET)
    public Ticket getOne(@PathVariable int id){
        Object o = null;
        String url = serviceUrl+"/ticket/"+id;
        Ticket t = restTemplate.getForObject(url, Ticket.class , o );
        return t;
    }

    @RequestMapping(value = "/ticket/{id}",method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable int id){
        restTemplate.delete(serviceUrl+"/ticket/{"+id+"}", id);
    }

    @GetMapping("/ticket/list")
    public List<Ticket> findAll(){
        List<Ticket> list = restTemplate.getForObject(serviceUrl + "/ticket/list", List.class);
        return list;
    }

    @GetMapping("/ticket/page")
    public Page<Ticket> getEntryByPageable(HttpServletRequest request) {
        String queryStr = request.getQueryString();
        System.out.println(queryStr);
        return restTemplate.getForObject(serviceUrl+"/ticket/page?"+queryStr,Page.class);
    }

    @PostMapping("/ticket/add")
    public Ticket addOne(Ticket ticket) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String,String>> entity = getTicketRequestEntity(ticket);
        return restTemplate.postForEntity(serviceUrl+"/ticket/add",entity,Ticket.class).getBody();
    }

    @PutMapping("/ticket/update")
    public void updateOne(Ticket ticket) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String,String>> entity = getTicketRequestEntity(ticket);
        restTemplate.put(serviceUrl+"/ticket/update",entity);
    }


    //@LoadBalanced
    //@Bean
    public RestTemplate getRequestTemplate(){
        return new RestTemplate();
    }


    private HttpEntity<MultiValueMap<String, String>> getTicketRequestEntity(Ticket ticket) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        //添加请求的参数
        params.add("id", ticket.getId()==null?"":ticket.getId()+"");
        params.add("name", ticket.getName());
        params.add("databaseName", ticket.getDatabaseName());
        params.add("stock", ticket.getStock()+"");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        return requestEntity;

    }

}
