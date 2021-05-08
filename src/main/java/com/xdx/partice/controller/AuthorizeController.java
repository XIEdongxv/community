package com.xdx.partice.controller;

import com.xdx.partice.dto.AccesstokenDTO;
import com.xdx.partice.dto.GithubUser;
import com.xdx.partice.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xdxstart
 * @create 2021-04-28 16:30
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.Client.id}")
    private String clientid;
    @Value("${github.Client.secret}")
    private String clientsecret;
    @Value("${github.Redirect.uri}")
    private String redirecturi;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirecturi);
        accesstokenDTO.setState(state);
        accesstokenDTO.setClient_id(clientid);
        accesstokenDTO.setClient_secret(clientsecret);
        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser user = githubProvider.getuser(accesstoken);
        System.out.println(user.getName());
        return "index";
    }
}
