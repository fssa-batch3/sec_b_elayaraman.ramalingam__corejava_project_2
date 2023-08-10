package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.util.StringUtil;

public class BusValidator {
	
	public static void validate(Bus bus ) throws Exception{
		
		if(bus == null)
			throw new Exception("Bus cannot be null");

		StringUtil.rejectIfInvalidString(bus.getBusNo(),"Bus No ");
	}

}
