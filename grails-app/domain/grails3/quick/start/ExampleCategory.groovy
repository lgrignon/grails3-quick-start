package grails3.quick.start

class ExampleCategory {
	
	String name
	
	static search = {
		name index: 'yes'
	}
}
