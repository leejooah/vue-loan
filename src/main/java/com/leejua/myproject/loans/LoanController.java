package com.leejua.myproject.loans;

import com.leejua.myproject.proxy.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired LoanParser parser;
    @Autowired LoanBaseRepository loanBaseRepository;
    @Autowired LoanOptionRepository loanOptionRepository;
    @Autowired Box<Object> box;

    @Transactional
    @GetMapping("/{nowPage}/{searchWord}/{order}")
    public Map<?, ?> list(@PathVariable("nowPage") int nowPage, @PathVariable("searchWord") String searchWord,@PathVariable("order") String order) {
        if(loanBaseRepository.count()==0 && loanOptionRepository.count()==0){
            parser.parse();
            parser.imageUploader();
        }
        System.out.println("넘어온 검색어="+searchWord+"\n nowPage="+nowPage+"\norder="+order);
        box.clear();
        box.put("list",loanBaseRepository.findList(PageRequest.of(nowPage,10, Sort.Direction.ASC,order),searchWord).getContent());
        box.put("count",loanBaseRepository.findList(PageRequest.of(nowPage,10,Sort.Direction.ASC,order),searchWord).getTotalElements());
        return box.get();
    }
    @Transactional
    @GetMapping("/detail/{loanBaseSeq}")
    public Map<?,?> selectOne(@PathVariable("loanBaseSeq")Long loanBaseSeq){
        box.clear();
        box.put("detail",   loanBaseRepository.findDetailById(loanBaseSeq));
        return box.get();
    }
}
