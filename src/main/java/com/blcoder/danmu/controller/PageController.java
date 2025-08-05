package com.blcoder.danmu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    /**
     * 弹幕系统首页
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 弹幕房间页面
     */
    @GetMapping("/room/{roomId}")
    public String room(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "room";
    }

    /**
     * 弹幕发送页面
     */
    @GetMapping("/send/{roomId}")
    public String send(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "send";
    }
}

