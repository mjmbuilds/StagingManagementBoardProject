package com.staging.service;

public class GenericService {
	
	/* fakeDao is a placeholder dao using collections of objects */
	//protected final String daoQualifier = "fakeDao";
	
	/* hibernateWorkaroundDao is a temporary version of the Hibernate dao
	 * that is using some workarounds to deal with some blockers 
	 * with getting the proper implementation to work.
	 * This should be replaced by using hibernateDao when the issues have 
	 * been resolved.*/
	//protected final String daoQualifier = "hibernateWorkaroundDao";

	/* hibernateDao is the Hibernate implementation of the dao*/
	protected final String daoQualifier = "hibernateDao";
	
}
