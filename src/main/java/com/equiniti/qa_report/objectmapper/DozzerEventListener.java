package com.equiniti.qa_report.objectmapper;

import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DozzerEventListener implements DozerEventListener {

  private static final Logger LOG = LoggerFactory.getLogger(DozzerEventListener.class);

  public void mappingStarted(DozerEvent event) {
	  LOG.debug("mappingStarted Called with:" + event.getClassMap().getDestClassToMap());
  }

  public void preWritingDestinationValue(DozerEvent event) {
	  LOG.debug("preWritingDestinationValue Called with:" + event.getClassMap().getDestClassToMap());
  }

  public void postWritingDestinationValue(DozerEvent event) {
	  LOG.debug("postWritingDestinationValue Called with:" + event.getClassMap().getDestClassToMap());
  }

  public void mappingFinished(DozerEvent event) {
	  LOG.debug("mappingFinished Called with:" + event.getClassMap().getDestClassToMap());
  }

}
