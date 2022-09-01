package com.optimgrowth.license.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.optimgrowth.license.entity.License;

@Service
public class LicenseService
{

	public License getLicense(String licenseId, String organizationId)
	{
		License license = new License();
		license.setId(new Random().nextInt(1000));
		license.setLicenseId(licenseId);
		license.setOrganizationId(organizationId);
		license.setDescription("Software Product");
		license.setProductName("OStock");
		license.setLiceseType("full");

		return license;
	}

	public String createLicense(License license, String organizationId)
	{
		String responseMessage = null;
		if (null != license) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format("This the post and object is: %s", license.toString());
		}

		return responseMessage;
	}

	public String updateLicense(License license, String organizationId)
	{
		String responseMessage = null;
		if (null != license) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format("This the put and object is: %s", license.toString());
		}

		return responseMessage;
	}

	public String deleteLicense(String licenseId, String organizationId)
	{
		String responseMessage = null;
		responseMessage = String.format("Deleting license with id %s, for organization %s", licenseId, organizationId);

		return responseMessage;
	}

}
