package com.softserveinc.charity.facade;

import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;

public interface NeedFacade {

    Need save(final NeedRequestData requestData);

}
