package app.controller;

import app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String getAdminPanel(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin-panel";
    }

    @PostMapping("/toggle/{id}")
    public String toggleUser(@PathVariable UUID id) {
        adminService.toggleUserStatus(id);
        return "redirect:/admin";
    }

    @PostMapping("/promote/{id}")
    public String promoteUser(@PathVariable UUID id) {
        adminService.promoteUser(id);
        return "redirect:/admin";
    }
}
