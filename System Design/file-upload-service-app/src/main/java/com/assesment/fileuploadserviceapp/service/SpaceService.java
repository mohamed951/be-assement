package com.assesment.fileuploadserviceapp.service;

import com.assesment.fileuploadserviceapp.entites.Space;

public interface SpaceService {

    Space createSpace(String spaceName, String userMail);
}
