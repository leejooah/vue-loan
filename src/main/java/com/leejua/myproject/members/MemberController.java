package com.leejua.myproject.members;

import com.leejua.myproject.proxy.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/members")
public class MemberController {
    static Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired Box<Object> box;
    @Autowired MemberRepository memberRepository;

    @PostMapping("/register")
    public Map<?,?> add (@RequestBody Member member) {
        box.clear();
        if (memberRepository.count()>0){
            Iterator<Member> itr =memberRepository.findAll().iterator();
            while (itr.hasNext()){
                if (itr.next().getEmail().equals(member.getEmail())){
                    box.put("duplication","true");
                    box.put("result","false");
                    break;
                }
            }
        }
        if(box.get("duplication")==null){
            Member memberEntity = new Member();
            memberEntity.setEmail(member.getEmail());
            memberEntity.setPasswd(member.getPasswd());
            memberRepository.save(member);
            box.put("result","true");
        }

        return box.get();
    }

    @PostMapping("/")
    public  Map<?,?> login(@RequestBody Member member){
        box.clear();
        Iterator<Member> itr = memberRepository.findAll().iterator();
        while (itr.hasNext()){
           Member next = itr.next();
            if (next.getEmail().equals(member.getEmail()) && next.getPasswd().equals(member.getPasswd())){
                    box.put("result","true");
                    box.put("member",next);
                    break;
            }
        }
        if(box.get("result")==null) box.put("result","false");
        System.out.println(box.get("result"));
        return box.get();
    }
    @DeleteMapping("/{email}/{deletePassword}")
    public Map<?,?> delete(@PathVariable("email") String email, @PathVariable("deletePassword")String passwd){
        box.clear();
        Iterator<Member> itr =memberRepository.findAll().iterator();
        while (itr.hasNext()){
            Member next = itr.next();
            if ((next.getEmail().equals(email) && next.getPasswd().equals(passwd))){
                memberRepository.deleteByEmail(email);
                box.put("deleteResult","true");
                break;
            }
        }
        if(box.get("deleteResult")==null) box.put("deleteResult","false");
        return box.get();
    }
    @PutMapping("/{updatePassword}")
    public Map<?,?> update(@PathVariable("updatePassword")String updatePassword, @RequestBody Member member){
        box.clear();
        System.out.println(updatePassword+member.toString());
        Iterator<Member> itr = memberRepository.findAll().iterator();
        while (itr.hasNext()){
            Member next = itr.next();
            if (next.getEmail().equals(member.getEmail()) && next.getPasswd().equals(member.getPasswd())){
               memberRepository.updatePasswd(member.getEmail(),updatePassword);
                box.put("updateResult","true");
                break;
            }
        }
        if(box.get("updateResult")==null) box.put("updateResult","false");
        System.out.println(box.get("updateResult"));
        return box.get();
    }
}
