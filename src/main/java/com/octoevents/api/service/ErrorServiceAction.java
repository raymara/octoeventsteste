package com.octoevents.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octoevents.api.model.Error;
import com.octoevents.api.model.form.ErrorService;
import com.octoevents.api.repository.ErrorRepository;

@Service
public class ErrorServiceAction implements ErrorService {
	
    private ErrorRepository errorLogRepository;

    @Autowired
    public void setErrorLogRepository(ErrorRepository error) {
        this.errorLogRepository = error;
    }

	@Override
	@Transactional
	public Error saveError(Error error) {
		return errorLogRepository.save(error);
	}
}