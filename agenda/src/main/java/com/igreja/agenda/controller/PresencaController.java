package com.igreja.agenda.controller;

import com.igreja.agenda.dto.PresencaRequest;
import com.igreja.agenda.dto.PresencaResponse;
import com.igreja.agenda.service.PresencaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/presencas")
public class PresencaController {

    private final PresencaService presencaService;

   @PostMapping
   public PresencaResponse responder (@RequestBody @Valid PresencaRequest presencaRequest, Authentication auth){
       return presencaService.responder(presencaRequest, auth.getName());
   }

    @GetMapping("/evento/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PresencaResponse> listarPorEvento(@PathVariable Long id) {
        return presencaService.listarPorEvento(id);
    }
}
