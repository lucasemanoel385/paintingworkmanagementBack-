package br.com.workmanagementW.controller;

import br.com.workmanagementW.dto.RegisterDiversPresenceDTO;
import br.com.workmanagementW.dto.RegisterPresenceDTO;
import br.com.workmanagementW.dto.ViewEmployeeIdAndNameDTO;
import br.com.workmanagementW.dto.ViewPresenceDTO;
import br.com.workmanagementW.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/presence")
public class PresenceController {

    @Autowired
    private PresenceService presenceService;

    @PostMapping("/register")
    public ResponseEntity createPresence(@RequestBody RegisterPresenceDTO dto, UriComponentsBuilder uriBuilder){

        var presence = presenceService.createPresence(dto);
        URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(presence.id()).toUri();
        return ResponseEntity.created(uri).body(presence);

    }

    @PostMapping("/register/presence-for-all")
    public ResponseEntity createPresenceForAll(@RequestBody RegisterDiversPresenceDTO dto){

        presenceService.createPresenceForAll(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("get-all")
    ResponseEntity<Page<ViewPresenceDTO>> findAllPresence(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(presenceService.findAllPresence(pageable));
    }

    @GetMapping("/get-presence-date")
    ResponseEntity<List<ViewEmployeeIdAndNameDTO>> findAllPresenceByWorkIdAndDateSpecify(@RequestParam Long workId, @RequestParam LocalDate dateSpecify) {
        return ResponseEntity.ok(presenceService.findAllPresenceByWorkIdAndDateSpecify(workId, dateSpecify));
    }

    @DeleteMapping("delete/{id}")
    public void deletePresence(@PathVariable Long id) {
        presenceService.deletePresence(id);
    }
}
