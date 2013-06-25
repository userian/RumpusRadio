package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.UploadAgreement;

public class UploadAgreementValidator implements Validator {
	public boolean supports(Class clazz)
    {
        return clazz.equals(UploadAgreement.class);
    }
	
	public void validate(Object command, Errors errors)
    {
    	ValidationUtils.rejectIfEmpty(errors, "agreementCheckbox", "agreementCheckbox.unchecked");
    }
}
