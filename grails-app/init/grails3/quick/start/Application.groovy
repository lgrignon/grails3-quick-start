package grails3.quick.start

import grails.boot.config.GrailsAutoConfiguration

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
	
	public static void main(String[] args) {
		println "launching application";
		GrailsApp.run(Application, args)
	}
	
	@Override
	public void onStartup(Map<String, Object> event) {
		println "\n\n-- Spring application startup --\n\n";
		super.onStartup(event);
	}
	
}