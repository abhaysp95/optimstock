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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
		License license = this.licenseService.getLicense(licenseId, organizationId);
		license.add(
				linkTo(methodOn(LicenseController.class)
					.getLicense(organizationId, license.getLicenseId())).withSelfRel(),
				linkTo(methodOn(LicenseController.class)
					.createLicense(organizationId, license, null)).withRel("createLicense"),
				linkTo(methodOn(LicenseController.class)
					.updateLicense(organizationId, license)).withRel("updateLicense"),
				linkTo(methodOn(LicenseController.class)
					.deleteLicense(organizationId, license.getLicenseId())).withRel("deleteLicense"));

		return ResponseEntity.ok(license);
	}

	// you can add links inside rest of the controller methods if they are
	// returning License too (or is there any other way to do this also ?)

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
