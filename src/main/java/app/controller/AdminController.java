package app.controller;

import app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/admin{id}")
    public String promoteUserToAdmin(@PathVariable UUID id) {
        adminService.promoteToAdmin(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/moderator/{id}")
    public String promoteUserToModerator(@PathVariable UUID id) {
        adminService.promoteToModerator(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/demote/moderator/{id}")
    public String demoteModerator(@PathVariable UUID id) {
        adminService.demoteModerator(id);

        return "redirect:/admin";
    }
}
