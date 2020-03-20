package com.octoevents.api.model.form;


import org.springframework.stereotype.Service;

import com.octoevents.api.model.Error;

@Service
public interface ErrorService {
  
	Error saveError(Error error);

}
