package com.optimgrowth.license.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimgrowth.license.entity.License;
import com.optimgrowth.license.service.LicenseService;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController
{

	@Autowired
	private LicenseService licenseService;

	@GetMapping(value = "/{licenseId}")
	public ResponseEntity<License> getLicense(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId)
	{
		return ResponseEntity.ok(this.licenseService.getLicense(licenseId, organizationId));
	}

	@PostMapping
	public ResponseEntity<String> createLicense(
			@PathVariable("organizationId") String organizationId,
			@RequestBody License request,
			@RequestHeader(value = "Accept-Language", required = false) Locale locale)
			// setting required: false, will give null if the mentioned header
			// is not present
	{
		return ResponseEntity.ok(this.licenseService.createLicense(request, organizationId, locale));
	}

	@PutMapping
	public ResponseEntity<String> updateLicense(
			@PathVariable("organizationId") String organizationId,
			@RequestBody License request)
	{
		return ResponseEntity.ok(this.licenseService.updateLicense(request, organizationId));
	}

	@DeleteMapping(value = "/{licenseId}")
	public ResponseEntity<String> deleteLicense(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId)
	{
		return ResponseEntity.ok(this.licenseService.deleteLicense(licenseId, organizationId));
	}
}
