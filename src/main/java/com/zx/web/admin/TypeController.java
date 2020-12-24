package com.zx.web.admin;

import com.zx.po.Type;
import com.zx.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types_input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types_input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Type type_0 = typeService.getTypeByName(type.getName());
        if (type_0 != null) {
            bindingResult.rejectValue("name","errorName","不能重复添加分类");
        }
        if (bindingResult.hasErrors()){
            return "admin/types_input";
        }
        Type type_1 = typeService.saveType(type);
        if(type_1 == null) {
            redirectAttributes.addFlashAttribute("message","增加失败");
        } else {
            redirectAttributes.addFlashAttribute("message","增加成功");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes){
        Type t_0 = typeService.getTypeByName(type.getName());
        if (t_0 != null) {
            bindingResult.rejectValue("name","errorName","不能重复添加分类");
        }
        if (bindingResult.hasErrors()){
            return "admin/types_input";
        }
        Type t_1 = typeService.updateType(id,type);
        if(t_1 == null) {
            redirectAttributes.addFlashAttribute("message","更新失败");
        } else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "分类已删除");
        return "redirect:/admin/types";
    }

}
