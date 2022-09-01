package com.optimgrowth.license.service;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.optimgrowth.license.entity.License;

@Service
public class LicenseService
{

	@Autowired
	MessageSource messages;

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

	public String createLicense(License license, String organizationId, Locale locale)
	{
		String responseMessage = null;
		if (null != license) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format(messages.getMessage("license.create.message", null, locale), license.toString());
		}

		return responseMessage;
	}

	public String updateLicense(License license, String organizationId)
	{
		String responseMessage = null;
		if (null != license) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format(messages.getMessage("license.update.message", null, null), license.toString());
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
