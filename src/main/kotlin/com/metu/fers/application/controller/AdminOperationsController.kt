package com.metu.fers.application.controller

import com.metu.fers.application.service.AdminService
import com.metu.fers.domain.entity.Admin
import com.metu.fers.domain.model.request.admin.CreateAdminRequest
import com.metu.fers.domain.model.request.admin.LogInAdminRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminOperationsController(private val adminService: AdminService) {

    @PostMapping("/create-admin")
    @ResponseStatus(HttpStatus.OK)
    fun createCustomer(@RequestBody(required = true) createAdminRequest: CreateAdminRequest): ResponseEntity<Admin> {
        return ResponseEntity.ok(adminService.createAdmin(createAdminRequest))
    }

    @GetMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    fun getAdmin(@RequestParam(required = true) email: String): ResponseEntity<Admin> {
        return ResponseEntity.ok(adminService.getAdmin(email))
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody(required = true) logInAdminRequest: LogInAdminRequest): ResponseEntity<Admin> {
        return ResponseEntity.ok(adminService.login(logInAdminRequest))
    }
}