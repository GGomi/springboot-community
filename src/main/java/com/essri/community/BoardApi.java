package com.essri.community;

import com.essri.community.service.BoardFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardApi {

    private final BoardFindService boardFindService;

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "id", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardFindService.findBoardById(idx));
        return "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardFindService.findBoardList(pageable));
        return "/board/list";
    }
}
