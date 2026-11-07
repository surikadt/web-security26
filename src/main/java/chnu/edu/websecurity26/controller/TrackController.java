/*
 * Author: Oleksii Hnidan
 * Project: web-security26 | Lab: 1 | Class: TrackController
 */
package chnu.edu.websecurity26.controller;

import chnu.edu.websecurity26.model.Track;
import chnu.edu.websecurity26.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService trackService;

    @GetMapping("/hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello, administrator!";
    }

    @GetMapping("/hello/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {
        return "Hello, user!";
    }

    @GetMapping("/hello/member")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String helloMember() {
        return "Hello, music catalog member!";
    }

    @GetMapping("/hello/superadmin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public String helloSuperadmin() {
        return "Hello, super administrator!";
    }

    @GetMapping
    public List<Track> getAll() {
        return trackService.getAll();
    }

    @GetMapping("/{id}")
    public Track get(@PathVariable String id) {
        return trackService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Track create(@RequestBody Track track) {
        return trackService.create(track);
    }

    @PutMapping("/{id}")
    public Track update(@PathVariable String id, @RequestBody Track track) {
        return trackService.update(id, track);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        trackService.delete(id);
    }
}
