package com.zx.web.admin;

import com.zx.po.Tag;
import com.zx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags_input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags_input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Tag tag_0 = tagService.getTagByName(tag.getName());
        if (tag_0 != null) {
            bindingResult.rejectValue("name","errorName","不能重复添加标签");
        }
        if (bindingResult.hasErrors()){
            return "admin/tags_input";
        }
        Tag tag_1 = tagService.saveTag(tag);
        if(tag_1 == null) {
            redirectAttributes.addFlashAttribute("message","增加失败");
        } else {
            redirectAttributes.addFlashAttribute("message","增加成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Tag t_0 = tagService.getTagByName(tag.getName());
        if (t_0 != null) {
            bindingResult.rejectValue("name","errorName","不能重复添加标签");
        }
        if (bindingResult.hasErrors()){
            return "admin/tags_input";
        }
        Tag t_1 = tagService.updateTag(id,tag);
        if(t_1 == null) {
            redirectAttributes.addFlashAttribute("message","更新失败");
        } else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "标签已删除");
        return "redirect:/admin/tags";
    }

}
